/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import exceptions.InvalidRecordFieldException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import model.User;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Кристина
 */
public interface Loader {

    public void addUser(Document document, User us) throws FileNotFoundException, TransformerException;

    public User readDocument(Document document) throws ParserConfigurationException, SAXException, IOException;

    public void writeDocument(Document document) throws TransformerConfigurationException, FileNotFoundException, TransformerException;
     public User readDocument(String log, String pass) throws SQLException, InvalidRecordFieldException;
     }


