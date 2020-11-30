package pri.hynes.aks.comp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
public class User {
 
    public User(Integer id, String name, String oid, String email){
        this.id = id;
        this.name = name;
        this.oid = oid;
        this.email = email;
    }

    @Id
    private Integer id;
 
    private String name;
 
    private String oid;

    private String email;
 
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
     * @return the oid
     */
    public String getOid() {
        return oid;
    }
 
    /**
     * @param oid the oid to set
     */
    public void setOid(String oid) {
        this.oid = oid;
    }
 
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
 
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }


    public String toString(){
        return "ID: " + this.id + "; Name: " + this.name + "; Oid: " + this.email + "; Email: " + this.email;
    }
 
    
}


