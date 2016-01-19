import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * Created by 47419119l on 19/01/16.
 */
@Embeddable
public class PKPrestec implements Serializable {
    long socioId;
    long llibreId;

    public PKPrestec(){

    }
    public PKPrestec(long socioId, long llibreId){
         this.socioId=socioId;
         this.llibreId=llibreId;
     }

    public long getLlibreId() {
        return llibreId;
    }

    public void setLlibreId(long llibreId) {
        this.llibreId = llibreId;
    }

    public long getSocioId() {
        return socioId;
    }

    public void setSocioId(long socioId) {
        this.socioId = socioId;
    }
}
