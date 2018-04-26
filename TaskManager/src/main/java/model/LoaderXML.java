/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.InvalidRecordFieldException;
import interfaces.Loader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import static model.Record.DATETIMEFORMATTER;
//ED3ED3ED3ED3ED3D3ED3ED

/**
 *
 * @author USER
 */
public class LoaderXML implements Loader {

    public LoaderXML() {
    }

    @Override
    public void addUser(Document document, User us) throws FileNotFoundException, TransformerException {
        if (us.getTaskLog().getRecords().size() > 0) {
            for (int i = 0; i < us.getTaskLog().getRecords().size(); i++) {
                //Получаем корневой элемент
                Node root = document.getDocumentElement();
                //создам новую задачу по элементно
                Element user1 = document.createElement("user");
                //<name>
                Element id = document.createElement("id");
                id.setTextContent(us.getId());
                Element name = document.createElement("name");
                //установка значения текста внутри тегов
                name.setTextContent("Название:");

                Element name1 = document.createElement("name1");
                name1.setTextContent(us.getTaskLog().getRecord(i).getName());
                Element description = document.createElement("description");
                description.setTextContent("Описание:");

                Element description1 = document.createElement("description1");
                description1.setTextContent(us.getTaskLog().getRecord(i).getDescription());
                Element timedate = document.createElement("timedate");
                timedate.setTextContent("время(дата)оповещения:");

                Element timedate1 = document.createElement("timedate1");
                timedate1.setTextContent(us.getTaskLog().getRecord(i).getTimeString());
                Element contacts = document.createElement("contacts");
                contacts.setTextContent("Контакты:");

                Element contacts1 = document.createElement("contacts1");
                contacts1.setTextContent(us.getTaskLog().getRecord(i).getContacts());
                //добавление внутренних элементов в элемент <Info>
                user1.appendChild(id);
                user1.appendChild(name);
                user1.appendChild(name1);
                user1.appendChild(description);
                user1.appendChild(description1);
                user1.appendChild(timedate);
                user1.appendChild(timedate1);
                user1.appendChild(contacts);
                user1.appendChild(contacts1);

                //добаляем инфо в корневой элемент
                root.appendChild(user1);

                // Записываем XML в файл
                writeDocument(document);
            }
        } else {
            writeDocument(document);
        }
    }

    @Override
    public User readDocument(Document document) throws ParserConfigurationException, SAXException, IOException {
        String id = null;
        String name1 = null;
        String description1 = null;
        String timedate1 = null;
        String contacts1 = null;

        LinkedList<Record> list = new LinkedList<>();

        NodeList nodeList = document.getElementsByTagName("user");

        for (int i = 0; i < nodeList.getLength(); i++) {
            // Выводим информацию по каждому из найденных элементов
            Node node = nodeList.item(i);

            //System.out.println("Текущий элемент: " + node.getNodeName());
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                Element element = (Element) node;
                id = element.getElementsByTagName("id").item(0).getTextContent();
                name1 = element.getElementsByTagName("name1").item(0).getTextContent();
                if (!DataCheck.nameCheck(name1)) {
                    String n = "";
                    for (int k = 0; k < 15; k++) {
                        n += name1.charAt(k);
                    }
                    name1 = n;
                }
                description1 = element.getElementsByTagName("description1").item(0).getTextContent();
                if (!DataCheck.descriptionCheck(description1)) {
                    String n = "";
                    for (int k = 0; k < 30; k++) {
                        n += description1.charAt(k);
                    }
                    description1 = n;
                }
                timedate1 = element.getElementsByTagName("timedate1").item(0).getTextContent();
                if (!DataCheck.timeCheck(timedate1)) {
                    Date date = new Date();
                    timedate1 = DATETIMEFORMATTER.format(date);
                }
                contacts1 = element.getElementsByTagName("contacts1").item(0).getTextContent();
                if (!DataCheck.contactsCheck(contacts1)) {
                    String n = "";
                    for (int k = 0; k < 15; k++) {
                        n += contacts1.charAt(k);
                    }
                    contacts1 = n;
                }

            }

            Record rec = null;
            try {
                rec = new Record(name1, description1, timedate1, contacts1);
            } catch (InvalidRecordFieldException ex) {
            } catch (ParseException ex) {
                Logger.getLogger(LoaderXML.class.getName()).log(Level.SEVERE, null, ex);
            }
            list.add(rec);
        }
        return new User(id, null, null, list);

    }

    @Override
    public void writeDocument(Document document) throws TransformerConfigurationException, FileNotFoundException, TransformerException {
        Transformer tr = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream("other.xml");
        StreamResult result = new StreamResult(fos);
        tr.transform(source, result);

    }

    @Override
    public User readDocument(String log, String pass) throws SQLException, InvalidRecordFieldException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
