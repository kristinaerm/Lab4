/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.InvalidRecordFieldException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.persistence.SequenceGenerator;
import javax.persistence.*;

/**
 *
 * @author USER
 */
@SequenceGenerator(name = "seqPK", sequenceName = "seqPK")
@Entity
@Table(name = "record")
public class Record implements Comparable, Serializable {

    @Id
    @GeneratedValue(generator = "seqPK")
    @Column(name = "id_task")
    private String id_task;

    @Column(name = "name_task")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "time_task")
    private Date time;

    @Column(name = "contacts")
    private String contacts;
    public static final SimpleDateFormat DATETIMEFORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public Record() {
        name = "";
        description = "";
        contacts = "";
        time = new Date();
        id_task = UUID.randomUUID().toString();
    }

    public Record(String n, String d, String t, String c) throws InvalidRecordFieldException, ParseException {
        if (DataCheck.nameCheck(n)) {
            if (DataCheck.descriptionCheck(d)) {
                if (DataCheck.contactsCheck(c)) {
                    name = n;
                    description = d;
                    contacts = c;
                    time = DATETIMEFORMATTER.parse(t);
                    id_task = UUID.randomUUID().toString();
                } else {
                    throw new InvalidRecordFieldException("Длина поля контактов не должна превышать 15 символов.");
                }
            } else {
                throw new InvalidRecordFieldException("Длина поля описания не должна превышать 30 символов.");
            }
        } else {
            throw new InvalidRecordFieldException("Длина поля названия не должна превышать 15 символов.");
        }

    }

    public void setId(String i) {
        id_task = i;
    }

    public void setTime(String t) throws InvalidRecordFieldException {
        if (DataCheck.timeCheck(t)) {
            try {
                time = DATETIMEFORMATTER.parse(t);
            } catch (ParseException e) {
                time = new Date();
            }
        } else {
            throw new InvalidRecordFieldException("Неправильный формат даты или прошедшее время. дд-мм-гггг чч:мм");
        }
    }

    public void setTimeDate(Date d) {
        time = d;
    }

    public void setName(String n) throws InvalidRecordFieldException {
        if (DataCheck.nameCheck(n)) {
            name = n;
        } else {
            throw new InvalidRecordFieldException("Длина поля не должна превышать 15 символов.");
        }
    }

    public void setDescription(String d) throws InvalidRecordFieldException {
        if (DataCheck.descriptionCheck(d)) {
            description = d;
        } else {
            throw new InvalidRecordFieldException("Длина поля не должна превышать 30 символов.");
        }
    }

    public void setContacts(String c) throws InvalidRecordFieldException {
        if (DataCheck.contactsCheck(c)) {
            contacts = c;
        } else {
            throw new InvalidRecordFieldException("Длина поля не должна превышать 15 символов.");
        }
    }

    public String getId() {
        return id_task;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getContacts() {
        return contacts;
    }

    public Date getTime() {
        return time;
    }

    public String getTimeString() {
        return DATETIMEFORMATTER.format(time);
    }

    @Override
    public int compareTo(Object t) {
        Record r = (Record) t;
        return time.compareTo(r.getTime());
    }

}
