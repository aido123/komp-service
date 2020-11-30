package pri.hynes.aks.comp.ui;

public class AnswerUI {
 
    public AnswerUI(Integer id, String description){
        this.id = id;
        this.description = description;
    }

    private Integer id;

    private String description;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
 
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }
 
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


    public String toString(){
        return "id: " + this.id + "; description: " + this.description;
    }
 
    
}
