package XML;

import java.io.File;

import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
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

public class XMLhandler {

	private static DocumentBuilderFactory dbFactory;
	private static DocumentBuilder dBuilder;
	private static Document doc;
	private static PlayerSave ps;
	private static File SaveFile;
	private static NodeList nList;
	private static Node nNode;
	private static final String s = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()
			+ "\\Memory\\MemorySave.xml";

	public static void createdoc() {
		File XMLFile = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Memory");
		if (!(XMLFile.exists())) {
			XMLFile.mkdirs();
			reset();
			dbFactory = DocumentBuilderFactory.newInstance();
			try {
				dBuilder = dbFactory.newDocumentBuilder();
				doc = dBuilder.newDocument();
				Element rootElement = doc.createElement("Game");
				doc.appendChild(rootElement);
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				try {
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File(s));
					transformer.transform(source, result);
				} catch (TransformerConfigurationException e) {
					System.out.println("There was an Error configuring the Transformer.");
					e.printStackTrace();
				}
			} catch (TransformerException tfe) {
				System.out.println("There was an Error setting up the Transformer.");
				tfe.printStackTrace();
			} catch (ParserConfigurationException e1) {
				System.out.println("There was an Error while configuring the Parser.");
				e1.printStackTrace();
			}
		}
	}

	public static PlayerSave readplayerinfo(int id) {
		String player = "Player_" + id;
		reset();
		ps = new PlayerSave();
		try {
			SaveFile = new File(s);
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
					ps.mintime = Integer.parseInt(eElement.getAttribute("maxtime"));
					ps.name = eElement.getAttribute("name");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("There was an error loading the Save File");
			e.printStackTrace();
		}
		return ps;
	}

	public static void writeplayerinfo(PlayerSave ps, int id) {
		String player = "Player_" + id;
		reset();
		try {
			SaveFile = new File(s);
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(SaveFile);
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName(player);
			if (nList.getLength() == 0) {
				Element newElement = doc.createElementNS(null, player);
				newElement.setAttribute("highscore", Integer.toString(ps.highscore));
				newElement.setAttribute("maxtime", Double.toString(ps.mintime));
				if (ps.name == null) {
					newElement.setAttribute("name", "none");
				} else {
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
						eElement.setAttribute("maxtime", Double.toString(ps.mintime));
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
	
	private static void reset() {
		dbFactory = null;
		dBuilder = null;
		doc = null;
		ps = null;
		SaveFile = null;
		nList = null;
		nNode = null;
	}
}
