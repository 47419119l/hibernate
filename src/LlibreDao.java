import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by 47419119l on 19/01/16.
 */
public class LlibreDao {
    private Session sesion;
    private Transaction tx;
    /**
     * Metode per guarda un objecte del tipos Llibre a la BBDD Postgresql
     * @param llibre
     * @return
     * @throws HibernateException
     */
    public long guardaLlibre(Llibre llibre) throws HibernateException

    {
        long id = 0;

        try
        {
            iniciaOperacion();
            id = (Long) sesion.save(llibre);
            tx.commit();
        } catch (HibernateException he)
        {
            manejaExcepcion(he);
            throw he;
        } finally
        {
            sesion.close();
        }

        return id;
    }

    /**
     * Metode per actualitzar un ojecte de clase LLibre que es troba dintre de la BBDD Postgresql.
     * @param soci
     * @throws HibernateException
     */
    public void actualizaLlibre(Llibre soci) throws HibernateException
    {
        try
        {
            iniciaOperacion();
            sesion.update(soci);
            tx.commit();
        } catch (HibernateException he)
        {
            manejaExcepcion(he);
            throw he;
        } finally
        {
            sesion.close();
        }
    }

    /**
     * Metode que elimina un registre de la BBDD
     * @param lli
     * @throws HibernateException
     */
    public void eliminaLlibre(Llibre lli) throws HibernateException
    {
        try
        {
            iniciaOperacion();
            sesion.delete(lli);
            tx.commit();
        } catch (HibernateException he)
        {
            manejaExcepcion(he);
            throw he;
        } finally
        {
            sesion.close();
        }
    }

    /**
     * Metode per consultar un llibre apartir del seu id.
     * @param llibreid
     * @return
     * @throws HibernateException
     */
    public Llibre obtenLlibre(long llibreid) throws HibernateException
    {
        Llibre contacto = null;
        try
        {
            iniciaOperacion();
            contacto = (Llibre) sesion.get(Llibre.class, llibreid);
        } finally
        {
            sesion.close();
        }

        return contacto;
    }

    /**
     * Metode per obtenir una llista amb tots els registres de Llibre
     * @return
     * @throws HibernateException
     */
    public List<Llibre> obtenListaLlibre() throws HibernateException
    {
        List<Llibre> listaLlibre = null;

        try
        {
            iniciaOperacion();
            listaLlibre = sesion.createQuery("from Llibre").list();
        } finally
        {
            sesion.close();
        }

        return listaLlibre;
    }


    /**
     * Metode per extreure els llibres que estan en prestec
     **/
    public static void llibresEnPrestec(){

    }

    /**
     * Metode per extreure llibres que té un soci
     * @param idSoci
     **/
    public static void llibresEnPrestecAunSoci(long idSoci){

    }

    /**
     *
     * @throws HibernateException
     */
    private void iniciaOperacion() throws HibernateException
    {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    /**
     *
     * @param he
     * @throws HibernateException
     */
    private void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    public List<Llibre> obtenListaLlibrePerTitol(String titol) throws HibernateException
    {
        List<Llibre> listaLlibre = null;

        try
        {
            iniciaOperacion();
            listaLlibre = sesion.createQuery("from Llibre where titol = '"+titol+"'").list();
        } finally
        {
            sesion.close();
        }

        return listaLlibre;
    }
    public List<Llibre> obtenListaLlibrePerEditorial(String editorial) throws HibernateException
    {
        List<Llibre> listaLlibre = null;

        try
        {
            iniciaOperacion();
            listaLlibre = sesion.createQuery("from Llibre where editorial = '"+editorial+"'").list();
        } finally
        {
            sesion.close();
        }

        return listaLlibre;
    }
    public List<Llibre> obtenListaLlibrePerEditorialTitol(String editorial,String titol) throws HibernateException
    {
        List<Llibre> listaLlibre = null;

        try
        {
            iniciaOperacion();
            listaLlibre = sesion.createQuery("from Llibre where editorial = '"+editorial+"'and titol = '"+titol+"'").list();
        } finally
        {
            sesion.close();
        }

        return listaLlibre;
    }
}
