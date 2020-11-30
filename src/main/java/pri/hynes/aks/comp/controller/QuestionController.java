package pri.hynes.aks.comp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import pri.hynes.aks.comp.ui.QuestionUI;
import pri.hynes.aks.comp.entity.User;
import java.util.List;
import com.google.gson.Gson; 
import com.google.gson.JsonObject;
import com.microsoft.azure.spring.autoconfigure.aad.UserPrincipal;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpStatusCodeException;
import java.util.Arrays;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

@CrossOrigin(origins = "*")
@RestController
public class QuestionController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PreAuthorize("hasRole('ROLE_CompGroup')")
    @PostMapping(value = "/api/levels/{id}/questions", produces = MediaType.APPLICATION_JSON_VALUE)
    public String levels(@PathVariable("id") long id, Authentication authentication) {

        String email = (String)((UserPrincipal)(authentication.getPrincipal())).getClaim("preferred_username");
        String name = (String)((UserPrincipal)(authentication.getPrincipal())).getClaim("name");
        String oid = (String)((UserPrincipal)(authentication.getPrincipal())).getClaim("oid");
        System.out.println("Authorities: "+authentication.getAuthorities());
        System.out.println("Claims: "+((UserPrincipal)(authentication.getPrincipal())).getClaims());

        User user = null;
        try{
            user = jdbcTemplate.queryForObject("Select * from user where oid = ? ", new Object[]{oid}, (rs, rowNum) ->
            new User(rs.getInt("id"), rs.getString("name"), rs.getString("oid"), rs.getString("email")));

            if(user == null){
                jdbcTemplate.update("Insert into user(name, oid, email) values(?,?,?)", name, oid, email);
            }
        }catch(Exception e){
            e.printStackTrace();
            return "{\"status\":\"500\", \"message\":\"Internal Error\"}";
        }

        List<QuestionUI> questions = jdbcTemplate.query("select a.id as id, a.levelid as levelid, a.description as description, a.type as type, case when b.qid is null then 'false' else 'true' end as complete from (select id, levelid, description, type from question where levelid=?) a left join (select qid from score join user on score.uid = user.id where user.oid=?) b on a.id = b.qid",
                new Object[] { id, oid },
                (resultSet, rowNum) -> new QuestionUI(resultSet.getInt("id"), resultSet.getInt("levelid"), resultSet.getString("description"), resultSet.getString("complete"),  resultSet.getString("type")));

        String json = new Gson().toJson(questions);

        return "{\"status\":\"200\", \"questions\":" + json + "}";

    }
}
