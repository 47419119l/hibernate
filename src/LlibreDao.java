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
}
