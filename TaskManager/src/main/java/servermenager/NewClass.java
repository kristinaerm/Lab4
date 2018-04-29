/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermenager;

import exceptions.InvalidRecordFieldException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LoaderSQL;
import model.Record;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author USER
 */
public class NewClass {

    public static void main(String[] args) throws ParseException, InvalidRecordFieldException {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
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
    }
}
