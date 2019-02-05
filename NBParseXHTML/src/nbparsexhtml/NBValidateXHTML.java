/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbparsexhtml;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author scuola
 */
public class NBValidateXHTML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        List corsi = null;
        Parser dom = new Parser();
        try {
            corsi = dom.parseDocument("sportello.xml");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(NBValidateXHTML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(NBValidateXHTML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NBValidateXHTML.class.getName()).log(Level.SEVERE, null, ex);
        }

        // iterazione della lista e visualizzazione degli oggetti
        System.out.println("Numero dei corsi: " + corsi.size());
        Iterator iterator = corsi.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }

    }

}
