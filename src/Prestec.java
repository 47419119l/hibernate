import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by 47419119l on 21/01/16.
 */
@Entity
public class Prestec implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    long idSoci;
    long idLlibre;
    String data_final;
    String data_inici;

    public Prestec(){

    }
    public Prestec(long idSoci, long idLlibre, String data_inici, String data_final)  {
        this.idSoci = idSoci;
        this.idLlibre= idLlibre;
        this.data_inici=data_inici;
        this.data_final=data_final;

    }

    public void setData_inici(String data_inici) {
        this.data_inici = data_inici;
    }

    public void setData_final(String data_final) {
        this.data_final = data_final;
    }

    public long getId() {
        return id;
    }

    public long getIdLlibre() {
        return idLlibre;
    }

    public long getIdSoci() {
        return idSoci;
    }

    public String getData_final() {
        return data_final;
    }

    public String getData_inici() {
        return data_inici;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdLlibre(long idLlibre) {
        this.idLlibre = idLlibre;
    }

    public void setIdSoci(long idSoci) {
        this.idSoci = idSoci;
    }

}
