package pri.hynes.aks.comp.ui;

public class QuestionUI {
 
    public QuestionUI(Integer id, Integer levelid, String description, String complete, String type){
        this.id = id;
        this.levelid = levelid;
        this.description = description;
        this.complete = complete;
        this.type = type;
    }

    private Integer id;

    private Integer levelid;
 
    private String description;

    private String complete;

    private String type;

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


    /**
     * @return the levelid
     */
    public Integer getLevelid() {
        return levelid;
    }
 
    /**
     * @param levelid the levelid to set
     */
    public void setLevelid(Integer levelid) {
        this.levelid = levelid;
    }
 
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
 
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


        /**
     * @return the complete
     */
    public String getComplete() {
        return complete;
    }
 
    /**
     * @param complete the complete to set
     */
    public void setComplete(String complete) {
        this.complete = complete;
    }


        /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }


    public String toString(){
        return "id: " + this.id + "; levelid: " + this.levelid + "; description: " + this.description+ "; complete: " + this.complete + "; type: " + this.type;
    }
 
    
}
