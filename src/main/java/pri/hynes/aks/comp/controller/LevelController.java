package pri.hynes.aks.comp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import pri.hynes.aks.comp.entity.Level;

@CrossOrigin(origins = "*")
@RestController
public class LevelController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
 
    @RequestMapping("/403")
    public String accessDenied() {
        return "{\"status\":\"403\", \"message\":\"403 error\"}";
    }

    @GetMapping(value = "/api/levels", produces = MediaType.APPLICATION_JSON_VALUE)
    public String levels() {

    List<Level> levels = jdbcTemplate.query("SELECT * FROM level",
            (resultSet, rowNum) -> new Level(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description")));

    String json = new Gson().toJson(levels);

    return "{\"status\":\"200\", \"levels\":" + json + "}";
    }
}
