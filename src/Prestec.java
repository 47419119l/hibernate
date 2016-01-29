import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

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
    Date data_final;
    Date data_inici;

    public Prestec(){

    }
    public Prestec(long idSoci, long idLlibre, Date data_inici, Date data_final)  {
        this.idSoci = idSoci;
        this.idLlibre= idLlibre;
        this.data_inici=data_inici;
        this.data_final=data_final;

    }

    public void setData_inici(Date data_inici) {
        this.data_inici = data_inici;
    }

    public void setData_final(Date data_final) {
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

    public Date getData_final() {
        return data_final;
    }

    public Date getData_inici() {
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
