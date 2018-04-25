/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.InvalidRecordFieldException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;

/**
 *
 * @author USER
 */
public class TaskLog implements Serializable{

    private static final String[] COLUMN_NAMES = {"№", "Название", "Время и дата", "Описание", "Контакты"};
    private LinkedList<Record> records;
    private final String id;

    public TaskLog(LinkedList<Record> rec) {

        id = UUID.randomUUID().toString();
        records = rec;
        sort();

    }

    public String getId() {
        return id;
    }

    public LinkedList<Record> getRecords() {
        return records;
    }

    public Object[] getRecordsArray() {
        return records.toArray();
    }

    public int getNumberOfRecords() {
        return records.size();
    }

    public Object[][] createData() {
        Object[][] data = new Object[records.size()][5];
        for (int i = 0; i < records.size(); i++) {
            data[i][0] = i;
            data[i][1] = this.getRecord(i).getName();
            data[i][2] = this.getRecord(i).getTimeString();
            data[i][3] = this.getRecord(i).getDescription();
            data[i][4] = this.getRecord(i).getContacts();
        }
        return data;
    }

    public void changeRecord(int n, String na, String ti, String des, String con) throws InvalidRecordFieldException {
        records.get(n);
        if ((!na.equals(""))) {
            records.get(n).setName(na);
        }
        if ((!ti.equals(""))) {
            records.get(n).setTime(ti);
        }
        if ((!des.equals(""))) {
            records.get(n).setDescription(des);
        }
        if ((!con.equals(""))) {
            records.get(n).setContacts(con);
        }
        sort();
    }

    public void changeRecord(String id, String na, String ti, String des, String con) throws InvalidRecordFieldException, IndexOutOfBoundsException {

        records.get(getNumberById(id));
        if ((!na.equals(""))) {
            records.get(getNumberById(id)).setName(na);
        }
        if ((!ti.equals(""))) {
            records.get(getNumberById(id)).setTime(ti);
        }
        if ((!des.equals(""))) {
            records.get(getNumberById(id)).setDescription(des);
        }
        if ((!con.equals(""))) {
            records.get(getNumberById(id)).setContacts(con);
        }
        sort();
    }

    public int getNumberById(String id) throws IndexOutOfBoundsException {
        int i = 0;
        while ((i < records.size()) && (!records.get(i).getId().equals(id))) {
            i++;
        }
        if (i == records.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            return i;
        }
    }

    public Record getRecord(int n) {

        return records.get(n);
    }

    public void addRecord(Record rec) {
        records.add(rec);
        sort();
    }

    public void deleteRecord(int n) {
        records.remove(n);
    }

    public void deleteRecord(String id) {
        int i = 0;
        while ((i < getNumberOfRecords()) && (!records.get(i).getId().equals(id))) {
            i++;
        }
        if (i < getNumberOfRecords()) {
            records.remove(i);
        }
    }

    private void sort() {
        Record temp = null;

        for (int j = 0; j < records.size(); j++) {
            for (int k = 0; k < records.size() - 1; k++) {
                if (records.get(k).compareTo(records.get(k + 1)) == 1) {
                    temp = records.get(k);
                    records.set(k, records.get(k + 1));
                    records.set(k + 1, temp);
                }
            }
        }
    }
}
