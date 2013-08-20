

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class NewTags extends ArrayList<String>
{
	/** Reads tags from a .txt file and stores them in a Hashtable
	 * @param fileName the path and name of the file containing tags
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> tags;
	@SuppressWarnings("unused")
	private int num;
	public NewTags(String fileName)
	{
		tags = new ArrayList<String>();
		try
		{
			Scanner file = new Scanner(new File(fileName));
			while (file.hasNextLine())
			{
				num = file.nextInt();
				String tag = file.nextLine();
				tags.add(tag);
			}
			file.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

	}
	/** Prints the list of tags in screen
	 */
	 public void printTags()
	{
		for (String tag: tags)
		{
//				System.out.print(i);
//				System.out.print("\t");
				System.out.println(tag);
			}
		}
	

	 public static void main(String[] args){
		 NewTags etiquetas = new NewTags("C:/Users/carlos/Downloads/PFC/Atlas Toronto/etiquetas.txt");
		 etiquetas.printTags();
	 }
}