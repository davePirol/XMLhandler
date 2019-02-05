package nbparsexhtml;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Parser {

    private List corsi;

    public Parser() {
        corsi = new ArrayList();
    }

    public List parseDocument(String filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element, element2;
        NodeList nodelist;
        Prof p;
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(filename);
        root = document.getDocumentElement();
        // generazione della lista degli elementi "libro"
        nodelist = root.getElementsByTagName("tr");
        if (nodelist != null && nodelist.getLength() > 0) {
            while (...){
            for (int i = 0; i < nodelist.getLength(); i += 2) {
                    element = (Element) nodelist.item(i);
                    element2 = (Element) nodelist.item(i + 2);
                    p = getProf(element, element2);
                    if (p != null) {
                        corsi.add(p);
                    }
                }
            }
        }
        return libri;
    }

    private Link getLink(Element element) {
        Link libro = null;
        String href = element.getAttribute("href");
        Element elementParent = (Element) element.getParentNode().getParentNode();
        String classe = getTextValue(elementParent, "td", 0);
        String tipoScuola = getTextValue(elementParent, "td", 1);
        String specializzazione = getTextValue(elementParent, "td", 2);
        if (classe != null) {
            libro = new Link(href, tipoScuola, specializzazione, classe);
        }
        return libro;
    }

    private Prof getProf(Element el, Element el2) {
        Prof p = null;

        // cerco il primo elemento nel primo tr
        Element elementParent = (Element) el.getParentNode().getParentNode();
        String materia = getTextValue(elementParent, "td", 0);
        String nome = getTextValue(elementParent, "td", 1);
        String giorno = getTextValue(elementParent, "td", 2);
        String ora = getTextValue(elementParent, "td", 3);

        //cerco le info del decondo tr
        Element elementParent2 = (Element) el2.getParentNode().getParentNode();
        String materia2 = getTextValue(elementParent2, "td", 0);
        String mail = getTextValue(elementParent2, "td", 1);
        String giorno2 = getTextValue(elementParent2, "td", 2);
        String ora2 = getTextValue(elementParent2, "td", 3);

        //sistemo i dati del professore
        ArrayList giorni = new ArrayList();
        ArrayList ore = new ArrayList();
        giorni.add(giorno);
        giorni.add(giorno2);
        ore.add(ora);
        ore.add(ora2);
        materia += " " + materia2;

        //creo l'oggetto del prof
        p = new Prof(materia, nome, mail, giorni, ore);
        return p;
    }

    // restituisce il valore testuale dell’elemento figlio specificato
    private String getTextValue(Element element, String tag, int child) {
        String value = null;
        NodeList nodelist;
        nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > child) {
            Node nodeChild = nodelist.item(child).getFirstChild();
            if ((nodeChild != null) && nodeChild.hasChildNodes()) {
                value = nodeChild.getFirstChild().getNodeValue();
            }
        }
        return value;
    }

}
