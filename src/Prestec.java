import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by 47419119l on 19/01/16.
 */
@Entity
public class Prestec implements Serializable  {

    @EmbeddedId
    PKPrestec pkPrestec;

    String data_inici;
    String data_final;

    public Prestec(){

    }

    public Prestec(PKPrestec pkPrestec, String data_final, String data_inici){
        this.pkPrestec= pkPrestec;
        this.data_final=data_final;
        this.data_inici= data_inici;

    }

    public PKPrestec getPkPrestec() {
        return pkPrestec;
    }

    public String getData_final() {
        return data_final;
    }

    public String getData_inici() {
        return data_inici;
    }

    public void setData_final(String data_final) {
        this.data_final = data_final;
    }

    public void setData_inici(String data_inici) {
        this.data_inici = data_inici;
    }

    public void setPkPrestec(PKPrestec pkPrestec) {
        this.pkPrestec = pkPrestec;
    }
}
