import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by 47419119l on 19/01/16.
 */

@Entity
public class Socis implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    String nom;
    String cognom;
    long edat;
    String direccio;
    String telefon ;

    public Socis(){

    }
    public Socis(String nom, String cognom, long edat, String direccio, String telefon){

        this.nom = nom;
        this.cognom= cognom;
        this.edat = edat;
        this.direccio = direccio;
        this.telefon = telefon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom(){
        return this.nom;
    }
    public  void setNom(String nom){
        this.nom= nom;
    }

    public String getCognom(){
        return this.cognom;
    }
    public void setCognom(String cognom){
        this.cognom= cognom;
    }

    public long getEdat(){
        return edat;
    }
    public void setEdat(long edat){
        this.edat=edat;
    }

    public String getDireccio(){
        return this.direccio;
    }
    public void setDireccio(String direccio){
        this.direccio= direccio;
    }

    public String getTelefon(){
        return this.telefon;
    }
    public void setTelefon(String telefon){
        this.telefon=telefon;
    }



}
