import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddFolder {

	private JFrame frame;
	private JTextField userFolderName;
	private JLabel errorMessage;
	static JLabel noFolderErrorMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFolder window = new AddFolder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddFolder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel labelForFoldername = new JLabel("Folder Name");
		labelForFoldername.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		labelForFoldername.setBounds(24, 31, 124, 16);
		frame.getContentPane().add(labelForFoldername);

		errorMessage = new JLabel("Please enter text");
		errorMessage.setVisible(false);
		errorMessage.setForeground(new Color(255, 0, 0));
		errorMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		errorMessage.setBounds(57, 81, 106, 16);
		frame.getContentPane().add(errorMessage);

		noFolderErrorMessage = new JLabel("Please create a folder before creating a task!");
		if (MainScreen.addTaskToFolder == true) {
			noFolderErrorMessage.setVisible(true);
		} else {
			noFolderErrorMessage.setVisible(false);
		}
		noFolderErrorMessage.setForeground(Color.RED);
		noFolderErrorMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		noFolderErrorMessage.setBounds(24, 109, 270, 16);
		frame.getContentPane().add(noFolderErrorMessage);

		userFolderName = new JTextField();
		userFolderName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okMethod();
			}
		});
		userFolderName.setColumns(10);
		userFolderName.setBounds(24, 54, 177, 26);
		frame.getContentPane().add(userFolderName);

		JButton addCategoryOkButton = new JButton("OK");
		addCategoryOkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okMethod();
			}
		});
		addCategoryOkButton.setBounds(282, 213, 117, 29);
		frame.getContentPane().add(addCategoryOkButton);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainScreen.addTaskToFolder = false;
				frame.setVisible(false);
			}
		});
		backButton.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		backButton.setBounds(6, 237, 50, 29);
		frame.getContentPane().add(backButton);

	}

	private void okMethod() {
		errorMessage.setVisible(false);
		if (userFolderName.getText().length() == 0) {
			errorMessage.setVisible(true);
		} else {
			ToDoList.addFolder(userFolderName.getText());
			MainScreen.addTaskToFolder = false;
			noFolderErrorMessage.setVisible(false);
			frame.setVisible(false);

		}
	}
}
