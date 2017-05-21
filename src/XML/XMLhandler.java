package XML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class XMLhandler {
	
	private static DocumentBuilderFactory dbFactory;
	private static DocumentBuilder dBuilder;
	private static Document doc;
	private static PlayerSave ps;
	private static File SaveFile;
	private static NodeList nList;
	private static Node nNode;
	
	
	public static PlayerSave readplayerinfo(int id){
		String player = "Player_" + id;
		reset();
	    ps = new PlayerSave();
	    try {
	    SaveFile = new File("C:\\Users\\Jakob Neu\\git\\memory\\src\\XML\\Save.xml");
	    dbFactory = DocumentBuilderFactory.newInstance();
	    dBuilder = dbFactory.newDocumentBuilder();
	    doc = dBuilder.parse(SaveFile);
	    doc.getDocumentElement().normalize();
	    nList = doc.getElementsByTagName(player);
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	        nNode = nList.item(temp);
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	            Element eElement = (Element) nNode;
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
	
	public static void writeplayerinfo(PlayerSave ps, int id){
		String player = "Player_" + id;
		reset();
	    try {
	    SaveFile = new File("C:\\Users\\Jakob Neu\\git\\memory\\src\\XML\\Save.xml");
	    dbFactory = DocumentBuilderFactory.newInstance();
	    dBuilder = dbFactory.newDocumentBuilder();
	    doc = dBuilder.parse(SaveFile);
	    doc.getDocumentElement().normalize();
	    nList = doc.getElementsByTagName(player);
	    if (nList.getLength() == 0) {
			Element newElement = doc.createElementNS(null, player);
			newElement.setAttribute("highscore", Integer.toString(ps.highscore));
			newElement.setAttribute("maxtime", Integer.toString(ps.maxtime));
			if (ps.name == null) {
				newElement.setAttribute("name", "none");	
			}
			else{
				newElement.setAttribute("name", ps.name);	
			}
			doc.getDocumentElement().appendChild(newElement);
			doc.getDocumentElement().setAttribute("test", "2");
		    Transformer xformer = TransformerFactory.newInstance().newTransformer();
		    Result result = new StreamResult(SaveFile);
			xformer.transform(new DOMSource(doc), result);
		} else {
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        nNode = nList.item(temp);
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		            Element eElement = (Element) nNode;
		            eElement.setAttribute("highscore", Integer.toString(ps.highscore));
		            eElement.setAttribute("maxtime", Integer.toString(ps.maxtime));
		            eElement.setAttribute("name", ps.name);
		            eElement.setAttribute("name", ps.name);
		            break;
		        }
		    }
		}

	    } catch (Exception e) {
	    System.out.println("There was an error writing the Save File");
	    e.printStackTrace();
	    }
	}
	
	private static void reset(){
		dbFactory = null;
		dBuilder = null;
		doc = null;
		ps = null;
		SaveFile = null;
		nList = null;
		nNode = null;
	}
}
