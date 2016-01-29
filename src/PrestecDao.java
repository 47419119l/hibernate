import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by 47419119l on 19/01/16.
 */
public class PrestecDao {
    private Session sesion;
    private Transaction tx;

    public long guardaLlibre(Prestec llibre) throws HibernateException

    {
        long id = 0;

        try {
            iniciaOperacion();
            id = (Long) sesion.save(llibre);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }

        return id;
    }

    public void actualizaPrestec(Prestec pr) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.update(pr);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public void eliminaPrestec(Prestec pr) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.delete(pr);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }


    public List<Prestec> obtenListaPrestec() throws HibernateException {
        List<Prestec> listaPrestec = null;

        try {
            iniciaOperacion();
            listaPrestec = sesion.createQuery("from Prestec").list();
        } finally {
            sesion.close();
        }

        return listaPrestec;
    }

    /**
     * @throws HibernateException
     */
    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public void mostrarLlibresPrestats(ObservableList list) {
        List<Llibre> listaPrestec = null;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://192.168.130.76:5432/sandra_altafaja", "sandra2", "sandra2");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT llibre.titol,llibre.id ,llibre.any_edicio, llibre.editorial, llibre.nombre_exemplars,llibre.nombre_pagines FROM llibre INNER JOIN prestec ON llibre.id = prestec.idllibre;");
            while (rs.next()) {
                String titol = rs.getString("titol");
                list.add(titol);


            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }
    public String mostrarSoci(int socisid) {
        List<Llibre> listaPrestec = null;
        Connection c = null;
        Statement stmt = null;
        String titol= "";
        String cognom="";
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://192.168.130.76:5432/sandra_altafaja", "sandra2", "sandra2");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT socis.nom, socis.cognom FROM socis  WHERE socis.id = "+socisid);
            while (rs.next()) {
                titol = rs.getString("nom");
                cognom = rs.getString("cognom");

            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return titol + cognom ;
    }
    public void mostrarSocisPrestats(ObservableList list,int socisid) {
        List<Llibre> listaPrestec = null;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://192.168.130.76:5432/sandra_altafaja", "sandra2", "sandra2");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT llibre.titol,llibre.id ,llibre.any_edicio, llibre.editorial, llibre.nombre_exemplars,llibre.nombre_pagines FROM llibre INNER JOIN prestec ON llibre.id = prestec.idllibre WHERE prestec.idsoci = "+socisid);
            while (rs.next()) {
                String titol = rs.getString("titol");
                list.add(titol);


            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

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
