package XML;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXML {

	  public static void main(String argv[]) {
	    try {
	    File SaveFile = new File("C:\\Users\\D067928\\git\\memory\\src\\XML\\Save.xml");
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(SaveFile);
	    doc.getDocumentElement().normalize();

	    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	    NodeList nList = doc.getElementsByTagName("Player_1");
	    System.out.println("----------------------------");

	    for (int temp = 0; temp < nList.getLength(); temp++) {
	        Node nNode = nList.item(temp);
	        System.out.println("Current Element :" + nNode.getNodeName());
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	            Element eElement = (Element) nNode;
	            
	            System.out.println(eElement.getNodeName());
	            //eElement.setAttributeNS(null, "maxtime", "2");
	            System.out.println(eElement.getAttribute("highscore"));
	            System.out.println(eElement.getAttribute("maxtime"));
	            System.out.println(eElement.getAttribute("name"));
	        }
	    }
	    
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
	  }
	}