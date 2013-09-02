package generated;



import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class gxd {
	private static ArrayList<DATA.ROW> tags = new ArrayList<DATA.ROW>();
	private static ArrayList<DATA.ROW> tagswithoutlevel = new ArrayList<DATA.ROW>();

	public static void main(String argv[]) {

		try {

			File fXmlFile = new File("C:\\Users\\carlos\\Desktop\\GXD_filtered.xml");
			/* The line above is to be substituted by the path of the file containing the atlas
			 * In case of an update, change the path by the one csontaining the latest version of
			 * the atlas ontology.
			 */
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("ROW");
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				DATA.ROW struct = FillFields(nNode);
				//tags.add(struct);
			}
			//for (DATA.ROW str: tags){
			//	PrintNames(str);
			//}
			for (DATA.ROW str: tagswithoutlevel){
				if(str.getStructureKey().equals("7005"))
				{
					int count = 0;
					while (count < tagswithoutlevel.size())
					{
						DATA.ROW candidate = tagswithoutlevel.get(count);
						if(candidate.getParentKey().equals("7005"))
						{
							str.addChild(candidate);
						}
					}
					break;
				}
				PrintNames(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static DATA.ROW FillFields(Node nNode) {
		DATA.ROW tag = new DATA.ROW();
		Element eElement = (Element) nNode;
		tag.setStructureKey((eElement.getElementsByTagName("_Structure_key").item(0).getTextContent()));
		tag.setParentKey((eElement.getElementsByTagName("_Parent_key").item(0).getTextContent()));
		tag.setStructureNameKey((eElement.getElementsByTagName("_StructureName_key").item(0).getTextContent()));
		tag.setStageKey((eElement.getElementsByTagName("_Stage_key").item(0).getTextContent()));
		tag.setEdinburghKey((eElement.getElementsByTagName("edinburghKey").item(0).getTextContent()));
		tag.setPrintName((eElement.getElementsByTagName("printName").item(0).getTextContent()));
		tag.setTreeDepth((eElement.getElementsByTagName("treeDepth").item(0).getTextContent()));
		tag.setPrintStop((eElement.getElementsByTagName("printStop").item(0).getTextContent()));
		tag.setTopoSort((eElement.getElementsByTagName("topoSort").item(0).getTextContent()));
		//NodeList children = eElement.getElementsByTagName("children");
		//if (children.getLength() >= 1)
		//{
		//	Node strNode = children.item(0);
		//	Element fElement = (Element) strNode;
		//	NodeList ChildDATA.ROWs = fElement.getElementsByTagName("DATA.ROW");
		//	for (int temp = 0; temp < ChildDATA.ROWs.getLength(); temp++) {
		//		Node ChildDATA.ROWsN = ChildDATA.ROWs.item(temp);
		//		DATA.ROW Ch = FillFields(ChildDATA.ROWsN);
		//		tag.addChild(Ch);
		//	}
		//}
		tagswithoutlevel.add(tag);
		return tag;
	}
	public static void PrintNames (DATA.ROW str) {
		System.out.println(str.getPrintName());
	}
}
