package pri.hynes.aks.comp.ui;

public class UserUI {
 
    public UserUI(String name, Integer score){
        this.name = name;
        this.score = score;
    }

    private String name;
 
    private Integer score;
 
 
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
 
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
 

    /**
     * @return the score
     */
    public Integer getScore() {
        return score;
    }
 
    /**
     * @param score the score to set
     */
    public void setScore(Integer score) {
                this.score = score;
    }
       

    public String toString(){
        return " Name: " + this.name  + "; score: " + this.score;
    }
 
    
}
