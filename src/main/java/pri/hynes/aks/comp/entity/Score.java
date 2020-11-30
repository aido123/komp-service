package pri.hynes.aks.comp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
 
import java.io.Serializable;

@Entity
public class Score implements Serializable {
 
    public Score(Integer uid, Integer qid){
        this.uid = uid;
        this.qid = qid;
    }
    @Id
    private Integer uid;

    @Id
    private Integer qid;
 

    /**
     * @return the uid
     */
    public Integer getUid() {
        return uid;
    }
 
    /**
     * @param uid the uid to set
     */
    public void setUid(Integer uid) {
        this.uid = uid;
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



    public String toString(){
        return "uid: " + this.uid + "; qid: " + this.qid;
    }
 
    
}
