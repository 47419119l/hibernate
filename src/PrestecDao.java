import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by 47419119l on 19/01/16.
 */
public class PrestecDao {
    private Session sesion;
    private Transaction tx;

    public long guardaPrestec(Prestec llibre) throws HibernateException

    {
        long id = 0;

        try
        {
            iniciaOperacion();
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

    public void actualizaPrestec(Prestec soci) throws HibernateException
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

    public void eliminaPrestec(Prestec lli) throws HibernateException
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

    public Prestec obtenPrestec(long llibreid) throws HibernateException
    {
        Prestec contacto = null;
        try
        {
            iniciaOperacion();
            contacto = (Prestec) sesion.get(Prestec.class, llibreid);
        } finally
        {
            sesion.close();
        }

        return contacto;
    }

    public List<Prestec> obtenListaPrestec() throws HibernateException
    {
        List<Prestec> listaPrestec = null;

        try
        {
            iniciaOperacion();
            listaPrestec = sesion.createQuery("from Prestec").list();
        } finally
        {
            sesion.close();
        }

        return listaPrestec;
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
