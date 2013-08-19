

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class ReadXMLFile {
	private static ArrayList<Structure> tags = new ArrayList<Structure>();
	private static ArrayList<Structure> tagswithoutlevel = new ArrayList<Structure>();

	public static void main(String argv[]) {

		try {

			File fXmlFile = new File("E:\\PruebaDOM\\src\\ref.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("structure");
			int temp = 0;
			//for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Structure struct = FillFields(nNode);
				tags.add(struct);
				//tagswithoutlevel.add(struct);
			//}
			
			for (Structure str: tags){
				PrintFields(str);
			}
			for (Structure str: tagswithoutlevel){
				PrintFields(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Structure FillFields(Node nNode) {
		Structure tag = new Structure();
		Element eElement = (Element) nNode;
		tag.setId((eElement.getElementsByTagName("id").item(0).getTextContent()));
		tag.setAtlasId((eElement.getElementsByTagName("atlas-id").item(0).getTextContent()));
		tag.setOntologyId((eElement.getElementsByTagName("ontology-id").item(0).getTextContent()));
		tag.setAcronym((eElement.getElementsByTagName("acronym").item(0).getTextContent()));
		tag.setName((eElement.getElementsByTagName("name").item(0).getTextContent()));
		tag.setColorHexTriplet((eElement.getElementsByTagName("color-hex-triplet").item(0).getTextContent()));
		tag.setGraphOrder((eElement.getElementsByTagName("graph-order").item(0).getTextContent()));
		tag.setStLevel((eElement.getElementsByTagName("st-level").item(0).getTextContent()));
		tag.setHemisphereId((eElement.getElementsByTagName("hemisphere-id").item(0).getTextContent()));
		tag.setParentStructureId((eElement.getElementsByTagName("parent-structure-id").item(0).getTextContent()));
		NodeList children = eElement.getElementsByTagName("children");
		if (children.getLength() >= 1)
		{
		Node strNode = children.item(0);
		Element fElement = (Element) strNode;
		NodeList ChildStructures = fElement.getElementsByTagName("structure");
		for (int temp = 0; temp < ChildStructures.getLength(); temp++) {
			Node ChildStructuresN = ChildStructures.item(temp);
			Structure Ch = FillFields(ChildStructuresN);
			tag.addChild(Ch);
		}
		}
		tagswithoutlevel.add(tag);
		return tag;
	}
	public static void PrintFields (Structure str) {
		//System.out.println(str.getAcronym());
		//System.out.println(str.getAtlasId());
		//System.out.println(str.getChildren().toString());
		//System.out.println(str.getColorHexTriplet());
		//System.out.println(str.getGraphOrder());
		//System.out.println(str.getHemisphereId());
		//System.out.println(str.getId());
		System.out.println(str.getName());
		//System.out.println(str.getOntologyId());
		//System.out.println(str.getParentStructureId());
		//System.out.println(str.getStLevel());
	}
}
