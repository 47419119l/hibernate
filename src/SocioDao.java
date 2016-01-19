import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by 47419119l on 19/01/16.
 */
public class SocioDao {
    private Session sesion;
    private Transaction tx;

    public long guardaContacto(Socis contacto) throws HibernateException

    {
        long id = 0;

        try
        {
            iniciaOperacion();
            id = (Long) sesion.save(contacto);
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

    public void actualizaContacto(Socis contacto) throws HibernateException
    {
        try
        {
            iniciaOperacion();
            sesion.update(contacto);
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

    public void eliminaContacto(Socis contacto) throws HibernateException
    {
        try
        {
            iniciaOperacion();
            sesion.delete(contacto);
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

    public Socis obtenContacto(long idContacto) throws HibernateException
    {
        Socis contacto = null;
        try
        {
            iniciaOperacion();
            contacto = (Socis) sesion.get(Socis.class, idContacto);
        } finally
        {
            sesion.close();
        }

        return contacto;
    }

    public List<Socis> obtenListaContactos() throws HibernateException
    {
        List<Socis> listaContactos = null;

        try
        {
            iniciaOperacion();
            listaContactos = sesion.createQuery("from Socis").list();
        } finally
        {
            sesion.close();
        }

        return listaContactos;
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
