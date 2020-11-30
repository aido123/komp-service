package pri.hynes.aks.comp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
 
@Entity
public class Answer {
 
    public Answer(Integer id, Integer qid, String description, String correct){
        this.id = id;
        this.qid = qid;
        this.description = description;
        this.correct = correct;
    }

    @Id
    private Integer id;

    private Integer qid;
 
    private String description;

    private String correct;

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
     * @return the qid
     */
    public Integer getQid() {
        return qid;
    }
 
    /**
     * @param qid the qid to set
     */
    public void setQid(Integer qid) {
        this.qid = qid;
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
     * @return the correct
     */
    public String getCorrect() {
        return correct;
    }
 
    /**
     * @param correct the correct to set
     */
    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String toString(){
        return "id: " + this.id + "; qid: " + this.qid + "; description: " + this.description+ "; correct: " + this.correct;
    }
 
    
}
