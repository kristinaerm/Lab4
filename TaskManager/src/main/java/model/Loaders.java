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
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Кристина
 */
public class Loaders implements Loader {

    private Loader loader;

    public void setLoaders(char ch) {
        if (ch == 'X' || ch == 'x') {
            loader = new LoaderXML();
        } else if (ch == 'S' || ch == 's') {
            loader = new LoaderSQL();
        }
    }

    @Override
    public void addUser(Document document, User us) throws FileNotFoundException, TransformerException {
        loader.addUser(document, us);
    }

    @Override
    public User readDocument(Document document) throws ParserConfigurationException, SAXException, IOException {
        return loader.readDocument(document);
    }

    @Override
    public void writeDocument(Document document) throws TransformerConfigurationException, FileNotFoundException, TransformerException {
        loader.writeDocument(document);
    }
     public User readDocument( String log, String pass) throws SQLException, InvalidRecordFieldException {
       return  loader.readDocument(log, pass);
     }
     }


