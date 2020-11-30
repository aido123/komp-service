package pri.hynes.aks.comp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import pri.hynes.aks.comp.ui.UserUI;
import java.util.List;
import com.google.gson.Gson; 

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @CrossOrigin
    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public String users() {

        List<UserUI> users = jdbcTemplate.query("select user.name as name, count(score.qid) as score from user left join score on  user.id = score.uid GROUP BY user.id ORDER BY score desc;",
                (resultSet, rowNum) -> new UserUI(resultSet.getString("name"), resultSet.getInt("score")));

        String json = new Gson().toJson(users);

        return "{\"status\":\"200\", \"users\":" + json + "}";

    }

}
