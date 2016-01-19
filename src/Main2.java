import java.util.List;

/**
 * Created by 47419119l on 19/01/16.
 */
public class Main2 {
    public static void main(String[] args) {
        SocioDao contactosDAO = new SocioDao ();
        LlibreDao llibreDao = new LlibreDao();
        PrestecDao prestecDao = new PrestecDao();
        Socis contactoRecuperado = null;
        long idAEliminar = 0;

        //Creamos tes instancias de Contacto
        Socis  contacto1 = new Socis ("pepe","pepe",20,"","");
        Socis  contacto2 = new Socis ("Sandra","pepe",20,"","");
        Socis  contacto3 = new Socis ("","pepe",20,"","");
        Llibre llibre = new Llibre("La casa de Bernarda Alba",2,"Seg",200,2015);

        PKPrestec idPrestec = new PKPrestec(1,1);
        Prestec prestec = new Prestec(idPrestec,"2032016","15022016");

        //Guardamos las tres instancias, guardamos el id del contacto1 para usarlo posteriormente
        contactosDAO.guardaSoci(contacto2);
        contactosDAO.guardaSoci(contacto3);

        llibreDao.guardaLlibre(llibre);
        prestecDao.guardaPrestec(prestec);


        /*
        //Modificamos el contacto 2 y lo actualizamos
        contacto2.setNom("Sandra 2");
        contactosDAO.actualizaSoci(contacto2);

        //Recuperamos el contacto1 de la base de datos
        contactoRecuperado = contactosDAO.obtenSoci(idAEliminar);
        System.out.println("Recuperamos a " + contactoRecuperado.getNom());

        //Eliminamos al contactoRecuperado (que es el contacto3)
       // contactosDAO.eliminaSoci(contactoRecuperado);

        //Obtenemos la lista de contactos que quedan en la base de datos y la mostramos
        List<Socis> listaContactos = contactosDAO.obtenListaSoci();
        System.out.println("Hay " + listaContactos.size() + "contactos en la base de datos");

        for(Socis c : listaContactos)
        {
            System.out.println("-> " + c.getNom());
        }

*/



    }
}
