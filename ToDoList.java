import java.io.*;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.lang.Object;

public class ToDoList {

	private static Vector<String> folderNames = new Vector<String>(); // stores the names of all the folders
	private static Vector<Vector<String>> folderContents = new Vector<Vector<String>>(); // stores the folders (as
																							// Vectors), and in each
																							// Vector there are Strings
																							// representing the tasks
	private static File tasksFile;
	public static int autoSelectedFolder = 0; // allows the user to quickly add multiple successive tasks to a folder

	public static void uploadFile() {
		// see if theres a file, if not, create one in a try catch block.
		try {
			tasksFile = new File("tasks.txt");
			if (tasksFile.createNewFile()) {
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static void fileToVector() {
		// read from the file and add data to the Vector
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(tasksFile));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.charAt(0) == 'f') { // folders will have 'f' as the first character, identifying them as
												// folders
					folderNames.add(line.substring(1));
					Vector<String> v = new Vector<String>();
					folderContents.add(v);
				} else { // the first letter will be 't' for task
					folderContents.get(folderContents.size() - 1).add(line.substring(1));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateTree() { // copying the Vector data into the tree
		MainScreen.tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("To-do") {
			{

				DefaultMutableTreeNode node_1;
				for (int i = 0; i < folderContents.size(); i++) {
					node_1 = new DefaultMutableTreeNode(folderNames.get(i));
					for (int task = 0; task < folderContents.get(i).size(); task++) {
						node_1.add(new DefaultMutableTreeNode(folderContents.get(i).get(task)));
					}
					add(node_1);
				}
			}
		}));
		AddTask.taskFolderComboBox.setModel(new DefaultComboBoxModel());
		for (int i = 0; i < folderContents.size(); i++) {
			AddTask.taskFolderComboBox.addItem(folderNames.get(i));
		}
	}

	public static void addTask(String name, int folderIndex) {

		folderContents.get(folderIndex).add(name);
		autoSelectedFolder = folderIndex;
		updateTree();
		rewriteToFile();

	}

	public static void addFolder(String name) {
		folderContents.add(new Vector<String>());
		folderNames.add(name);
		updateTree();
		rewriteToFile();
	}

	public static void deleteNode(int itemIndex, int folderIndex) { // if the user deletes a TASK
		folderContents.get(folderIndex).remove(itemIndex);
		rewriteToFile();

	}

	public static void deleteNode(int folderIndex) { // if the user deletes a FOLDER
		folderContents.get(folderIndex).removeAllElements();
		folderContents.remove(folderIndex);
		folderNames.remove(folderIndex);
		AddTask.taskFolderComboBox.removeItemAt(folderIndex);
		if (folderIndex == autoSelectedFolder) {
			autoSelectedFolder = 0;
		} else if (autoSelectedFolder > folderIndex) {
			autoSelectedFolder--;
		}

		rewriteToFile();
	}

	public static void rewriteToFile() {
		tasksFile = new File("tasks.txt");
		try {
			FileWriter myWriter = new FileWriter("tasks.txt");
			for (int i = 0; i < folderContents.size(); i++) {
				myWriter.write("f" + folderNames.get(i) + "\n");
				for (int task = 0; task < folderContents.get(i).size(); task++) {
					myWriter.write("t" + folderContents.get(i).get(task) + "\n");
				}

			}

			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

}
