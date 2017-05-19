package XML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class XMLhandler {
	public static PlayerSave readplayerinfo(String player){
	    PlayerSave ps = new PlayerSave();
	    try {
	    File SaveFile = new File("C:\\Users\\D067928\\git\\memory\\src\\XML\\Save.xml");
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(SaveFile);
	    doc.getDocumentElement().normalize();
	    
	    NodeList nList = doc.getElementsByTagName(player);

	    for (int temp = 0; temp < nList.getLength(); temp++) {
	        Node nNode = nList.item(temp);
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	            Element eElement = (Element) nNode;
	            //eElement.setAttributeNS(null, "maxtime", "2");
	            ps.highscore = Integer.parseInt(eElement.getAttribute("highscore"));
	            ps.maxtime   = Integer.parseInt(eElement.getAttribute("maxtime"));
	            ps.name      = eElement.getAttribute("name");
	            break;
	        }
	    }
	    } catch (Exception e) {
	    System.out.println("There was an error loading the Save File");
	    e.printStackTrace();
	    }
	    return ps;
	  }
}
