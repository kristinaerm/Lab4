/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.InvalidRecordFieldException;
import interfaces.Loader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Кристина
 */
public class LoaderSQL implements Loader {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public void addUser(Document document, User us) throws FileNotFoundException, TransformerException {
        try {
            clearDatabase(us);
            addDataInTableUser(us.getId(), us.getPassword(), us.getLogin());
            for (int i = 0; i < us.getTaskLog().getNumberOfRecords(); i++) {
                addDataInTableTask(us.getTaskLog().getRecord(i).getName(), us.getTaskLog().getRecord(i).getTime(), us.getTaskLog().getRecord(i).getContacts(), us.getTaskLog().getRecord(i).getDescription());
                addDataInTableUserTask(us.getId(), us.getTaskLog().getRecord(i).getId());
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User readDocument(String log, String pass) throws SQLException, InvalidRecordFieldException {
        return null;
    }

    @Override
    public void writeDocument(Document document) throws TransformerConfigurationException, FileNotFoundException, TransformerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addDataInTableTask(String name, String time, String contacts, String description) throws SQLException, NamingException {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Record rec = new Record(name, description, time, contacts);
            session.save(rec);
            tx.commit();
        } catch (InvalidRecordFieldException | ParseException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void addDataInTableUser(String idUser, String passworduser, String loginuser) throws SQLException {
        try {
            Locale.setDefault(Locale.ENGLISH);

            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/TestDB");
            try (Connection conn = ds.getConnection(); Statement st = conn.createStatement()) {
                st.executeUpdate("INSERT INTO users (id_user, login, password) VALUES (" + idUser + ", " + passworduser + "," + loginuser + ")");
            }
        } catch (NamingException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addDataInTableUserTask(String idUser, Integer idTask) throws SQLException {
        try {
            Locale.setDefault(Locale.ENGLISH);

            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/TestDB");
            try (Connection conn = ds.getConnection(); Statement st = conn.createStatement()) {
                st.executeUpdate("INSERT INTO usertask (id_user,id_task) VALUES (" + idUser + "," + idTask + ")");
            }
        } catch (NamingException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteDataInTableTask(int idTask) throws SQLException, NamingException {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hqlUpdate = "delete from Record where id_task = :id";
            int updatedEntities = session.createQuery(hqlUpdate)
                    .setString("id", String.valueOf(idTask))
                    .executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void deleteDataInTableUser(String idUser) throws SQLException, NamingException {
        Locale.setDefault(Locale.ENGLISH);
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/TestDB");
        try (Connection conn = ds.getConnection(); Statement st = conn.createStatement()) {
            st.executeUpdate("DELETE FROM users WHERE idUser='" + idUser + "'");
        }
    }

    public void deleteDataInTableUserTask(String idUser, String idTask) throws SQLException, NamingException {
        Locale.setDefault(Locale.ENGLISH);
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/TestDB");
        try (Connection conn = ds.getConnection(); Statement st = conn.createStatement()) {
            st.executeUpdate("DELETE FROM TASK WHERE ID_TASK= '" + idTask + "'");
        }
    }

    public void changeDataInTableTask(Record rPast, Record rFuture) throws SQLException {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hqlUpdate = "update Record set name_task = :name, description = :desc, time_task = :time, contacts = :cont where id_task = :id";
            int updatedEntities = session.createQuery(hqlUpdate)
                    .setString("name", rFuture.getName())
                    .setString("desc", rFuture.getDescription())
                    .setString("time", rFuture.getTime())
                    .setString("cont", rFuture.getContacts())
                    .setString("id", rFuture.getId().toString())
                    .executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void changeDataInTableUser(String idUser, String passworduser, String loginuser) throws SQLException {
        try {
            Locale.setDefault(Locale.ENGLISH);

            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/TestDB");
            try (Connection conn = ds.getConnection(); Statement st = conn.createStatement()) {
                st.executeUpdate("UPDATE users SET login = " + loginuser + ", password = " + passworduser + " WHERE idUser = " + idUser);
            }
        } catch (NamingException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clearDatabase(User us) throws SQLException, NamingException {
        deleteDataInTableUser(us.getId());
        for (int i = 0; i < us.getTaskLog().getNumberOfRecords(); i++) {
            deleteDataInTableTask(us.getTaskLog().getRecord(i).getId());
            deleteDataInTableUserTask(us.getId(), us.getTaskLog().getRecord(i).getId().toString());
        }
    }

    @Override
    public User readDocument(Document document) throws ParserConfigurationException, SAXException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LinkedList<Record> selectInTableTask() throws ParseException {
        LinkedList<Record> recs = new LinkedList<>();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Record");
        List records = query.list();
        for (Iterator it = records.iterator(); it.hasNext();) {
            Record rec = (Record) it.next();
            recs.add(rec);
        }
        session.close();
        return recs;
    }

    public Record selectTask(int idTask) throws ParseException {
        LinkedList<Record> recs = selectInTableTask();
        int i = 0;
        while ((i < recs.size()) && (!recs.get(i).getId().equals(idTask))) {
            i++;
        }
        if (i != recs.size()) {
            return recs.get(i);
        }
        return null;
    }

    public Object[] selectTime() {
        Session session = null;
        long smallesttime = -1;
        long curTime;
        long notifTime;
        Record resultrec = new Record();
        Object[] o = new Object[2];
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("from Record r order by time_task");
            List records = query.list();

            for (Iterator it = records.iterator(); it.hasNext();) {
                Record rec = (Record) it.next();
                curTime = System.currentTimeMillis();
                SimpleDateFormat DATETIMEFORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                notifTime = DATETIMEFORMATTER.parse(rec.getTime()).getTime();
                notifTime -= curTime;
                if ((notifTime > 0) && (smallesttime < 0)) {
                    smallesttime = notifTime / 1000;
                    resultrec.setContacts(rec.getContacts());
                    resultrec.setDescription(rec.getDescription());
                    resultrec.setId(rec.getId());
                    resultrec.setName(rec.getName());
                    resultrec.setTime(rec.getTime());
                }
            }
        } catch (InvalidRecordFieldException | ParseException | HibernateException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();
            o[0] = smallesttime;
            o[1] = resultrec;
        }
        return o;
    }
}
