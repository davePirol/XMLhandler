package nbparsexhtml;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Parser {

    private List corsi,consigli;

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
        
        nodelist = root.getElementsByTagName("tr");
        int count=0;
        if (nodelist != null && nodelist.getLength() > 0) {
            
            boolean trovato=false;
            
            while(!trovato){
                if(nodelist.item(count).getFirstChild().getTextContent().equals("DISCIPLINA"))
                    trovato=true;
                count++;
            }
            for (int i = count; i < nodelist.getLength(); i += 2) {
                element = (Element) nodelist.item(i);
                element2 = (Element) nodelist.item(i + 1);


                if(element.getFirstChild().getTextContent().contains("N.B."))
                    break;
                else{
                    p = getProf(element, element2);
                    if (p != null) {
                        corsi.add(p);

                    }
                }
            }
        }
        return corsi;
    }
    
    public List parseConsiglio(String filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element, element2;
        NodeList nodelist;
        Consigli c;
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(filename);
        root = document.getDocumentElement();
        nodelist = root.getElementsByTagName("tr");
        
        
        return consigli;
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
        //Element elementParent = (Element) el.getParentNode();
        String materia = el.getChildNodes().item(0).getTextContent(); //getTextValue(elementParent, "td", 0);
        String nome = el.getChildNodes().item(1).getTextContent(); //getTextValue(elementParent, "td", 1);
        String giorno = el.getChildNodes().item(2).getTextContent(); //getTextValue(elementParent, "td", 2);
        String ora = el.getChildNodes().item(3).getTextContent(); //getTextValue(elementParent, "td", 3);

        //cerco le info del decondo tr
        //Element elementParent2 = (Element) el2.getParentNode();
        String materia2 = el2.getChildNodes().item(0).getTextContent();//getTextValue(elementParent2, "td", 0);
        String mail = el2.getChildNodes().item(1).getTextContent();//getTextValue(elementParent2, "td", 1);
        String giorno2 = el2.getChildNodes().item(2).getTextContent();//getTextValue(elementParent2, "td", 2);
        String ora2 = el2.getChildNodes().item(3).getTextContent();//getTextValue(elementParent2, "td", 3);

        //sistemo i dati del professore
        ArrayList giorni = new ArrayList();
        ArrayList ore = new ArrayList();
        giorni.add(giorno);
        giorni.add(giorno2);
        ore.add(ora);
        ore.add(ora2);
        materia += " " + materia2;

        //creo l'oggetto del prof
        p = new Prof(materia,nome,mail,giorni,ore);
        return p;
    }
    
    public Consigli getConsigli(NodeList list){
        Consigli c=null;
        ArrayList classi=new ArrayList();
        ArrayList aule=new ArrayList();
        ArrayList oreDoc=new ArrayList();
        ArrayList oreStud=new ArrayList();
        
        String lotto=list.item(0).getFirstChild().getTextContent();
        String giorno=list.item(1).getChildNodes().item(1).getTextContent();
        oreDoc.add(list.item(3).getChildNodes().item(0).getTextContent());
        oreStud.add(list.item(3).getChildNodes().item(1).getTextContent());
        classi.add(list.item(3).getChildNodes().item(2).getTextContent());
        classi.add(list.item(3).getChildNodes().item(3).getTextContent());
        oreDoc.add(list.item(4).getChildNodes().item(0).getTextContent());
        oreStud.add(list.item(4).getChildNodes().item(1).getTextContent());
        classi.add(list.item(4).getChildNodes().item(2).getTextContent());
        classi.add(list.item(4).getChildNodes().item(3).getTextContent());
        oreDoc.add(list.item(5).getChildNodes().item(0).getTextContent());
        oreStud.add(list.item(5).getChildNodes().item(1).getTextContent());
        classi.add(list.item(5).getChildNodes().item(2).getTextContent());
        classi.add(list.item(5).getChildNodes().item(3).getTextContent());
        String descrizione=list.item(6).getChildNodes().item(0).getTextContent();
        aule.add(list.item(6).getChildNodes().item(2).getTextContent());
        aule.add(list.item(6).getChildNodes().item(3).getTextContent());
        aule.add(list.item(6).getChildNodes().item(4).getTextContent());
        
        c=new Consigli(giorno,oreDoc,oreStud,classi,aule,lotto,descrizione);
        
        return c;
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
