package pri.hynes.aks.comp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import pri.hynes.aks.comp.ui.AnswerUI;
import java.util.List;
import com.google.gson.Gson; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
@RestController
public class AnswerController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping(value = "/api/levels/{lid}/questions/{qid}/answers", produces = MediaType.APPLICATION_JSON_VALUE)
    public String answers(@PathVariable("lid") long lid, @PathVariable("qid") long qid) {

    List<AnswerUI> answers = jdbcTemplate.query("SELECT * FROM answer where qid = ?", new Object[] { qid },
            (resultSet, rowNum) -> new AnswerUI(resultSet.getInt("id"), resultSet.getString("description")));

    String json = new Gson().toJson(answers);

    return "{\"status\":\"200\", \"answers\":" + json + "}";
    }

}
