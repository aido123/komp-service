package pri.hynes.aks.comp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
 
@Entity
public class Question {
 
    public Question(Integer id, Integer levelid, String description, String setup, String answerQuery, String answerValue, String type){
        this.id = id;
        this.levelid = levelid;
        this.description = description;
        this.setup = setup;
        this.answerQuery = answerQuery;
        this.answerValue = answerValue;
        this.type = type;
    }

    @Id
    private Integer id;

    private Integer levelid;
 
    private String description;

    private String setup;

    private String answerQuery;

    private String answerValue;

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
     * @param levelid the qid to set
     */
    public void setQid(Integer levelid) {
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
     * @return the setup
     */
    public String getSetup() {
        return setup;
    }
 
    /**
     * @param setup the setup to set
     */
    public void setSetup(String setup) {
        this.setup = setup;
    }

     /**
     * @return the answerQuery
     */
    public String getAnswerQuery() {
        return answerQuery;
    }
 
    /**
     * @param answerQuery the answerQuery to set
     */
    public void setAnswerQuery(String answerQuery) {
        this.answerQuery = answerQuery;
    }


         /**
     * @return the answerValue
     */
    public String getAnswerValue() {
        return answerValue;
    }
 
    /**
     * @param answerValue the answerValue to set
     */
    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
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
        return "id: " + this.id + "; levelid: " + this.levelid + "; description: " + this.description+ "; setup: " + this.setup + "; answerQuery: " + this.answerQuery + "; answerValue: " + this.answerValue;
    }
 
    
}
