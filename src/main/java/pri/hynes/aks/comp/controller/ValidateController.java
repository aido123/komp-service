package pri.hynes.aks.comp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
import java.io.*;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

@CrossOrigin(origins = "*")
@RestController
public class ValidateController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PreAuthorize("hasRole('ROLE_CompGroup')")
    @PostMapping(value = "/api/validate/levels/{lid}/questions/{qid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String validate(@PathVariable("lid") long lid, @PathVariable("qid") long qid, @RequestParam(name="ansid", required = false, defaultValue = "0") String ansid, Authentication authentication) {

        String oid = (String)((UserPrincipal)(authentication.getPrincipal())).getClaim("oid");

        String answerquery = (String) jdbcTemplate.queryForObject("select answer_query from question where id = ?",
                new Object[] { qid },
                String.class);

        String answervalue = (String) jdbcTemplate.queryForObject("select answer_value from question where id = ?",
                new Object[] { qid },
                String.class);

        Integer userId = (Integer) jdbcTemplate.queryForObject("select id from user where oid = ?",
                new Object[] { oid },
                Integer.class);


        if (!"".equals(answerquery))
        {
                String answer = runKubectlBinary(answerquery + " -n "+oid);
                if (!answervalue.equals(answer)){
                    return "{\"status\":\"200\", \"validation\":\"incorrect\" , \"message\":\"Answer Incorrect, please try again\"}";
                }
        } else {
            String correctvalue = (String) jdbcTemplate.queryForObject("select correct from answer where id = ?",
                        new Object[] { ansid },
                        String.class);

            if (!"true".equals(correctvalue)){
                return "{\"status\":\"200\", \"validation\":\"incorrect\" , \"message\":\"Answer Incorrect, please try again\"}";
            }
        }

        try {
               jdbcTemplate.update("REPLACE INTO score(qid, uid) VALUES(?,?)", qid, userId);
        }catch(Exception e) {
            e.printStackTrace();
            return "{\"status\":\"500\", \"message\":\"Internal Error creating score\"}";
        }

        return "{\"status\":\"200\", \"validation\":\"correct\" , \"message\":\"Answer Correct\"}";
    }


    //TODO API all this!
    private String runKubectlBinary(String command){
        String output = "";
        try{
            Runtime rt = Runtime.getRuntime();

            String[] commands;

            if (SystemUtils.IS_OS_WINDOWS){
                commands = new String[]{"cmd.exe", "/c", command};
            }else{
                commands = new String[]{"bash", "-c", command};
            }

            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                InputStreamReader(proc.getErrorStream()));
            
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                output += s;
            }

            while ((s = stdError.readLine()) != null) {
                output += s;
            }
        } catch(Exception e){
            System.out.println(e);
        }

        return output;
    }

}