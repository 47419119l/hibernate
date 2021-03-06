import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Created by 47419119l on 19/01/16.
 */
public class SocioDao {

    private Session sesion;
    private Transaction tx;

    public long guardaSoci(Socis soci) throws HibernateException

    {
        long id = 0;

        try {
            iniciaOperacion();
            id = (Long) sesion.save(soci);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }

        return id;
    }

    public void actualizaSoci(Socis soci) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.update(soci);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public void eliminaSoci(Socis soci) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.delete(soci);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public Socis obtenSoci(long sociId) throws HibernateException {
        Socis contacto = null;
        try {
            iniciaOperacion();
            contacto = (Socis) sesion.get(Socis.class, sociId);
        } finally {
            sesion.close();
        }

        return contacto;
    }

    public List<Socis> obtenListaSoci() throws HibernateException {
        List<Socis> listaContactos = null;

        try {
            iniciaOperacion();
            listaContactos = sesion.createQuery("from Socis").list();
        } finally {
            sesion.close();
        }

        return listaContactos;
    }

    public List<Socis> consultaSociPerNom(String nom) throws HibernateException {
       List<Socis> contacto = null;
        try {
            iniciaOperacion();
            contacto =  sesion.createQuery("from Socis where nom = '" + nom + "'").list();
        } finally {
            sesion.close();
        }
        return contacto;
    }
    public List<Socis> consultaSociPerNomCognom(String nom,String cognom) throws HibernateException {
        List<Socis> contacto = null;
        try {
            iniciaOperacion();
            contacto =  sesion.createQuery("from Socis where nom = '" + nom + "'and cognom = '"+cognom+"'").list();
        } finally {
            sesion.close();
        }
        return contacto;
    }
    public List<Socis> consultaSociPerCognom(String cognom) throws HibernateException {
        List<Socis> contacto = null;
        try {
            iniciaOperacion();
            contacto =  sesion.createQuery("from Socis where cognom = '" + cognom + "'").list();
        } finally {
            sesion.close();
        }
        return contacto;
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
