import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddTask {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	static JComboBox taskFolderComboBox = new JComboBox();
	private JLabel errorMessage;
	private JLabel noFolderErrorMessage;
	private JLabel errorMessageChooseFolder;
	JRadioButton regularRadioButton;
	private JRadioButton highRadioButton;
	private JTextField userTaskName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTask window = new AddTask();
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
	public AddTask() {
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

		JLabel taskNameLabel = new JLabel("Name");
		taskNameLabel.setBounds(20, 17, 61, 16);
		frame.getContentPane().add(taskNameLabel);

		userTaskName = new JTextField();
		userTaskName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okMethod();
			}
		});
		userTaskName.setBounds(20, 45, 183, 26);
		frame.getContentPane().add(userTaskName);
		userTaskName.setColumns(10);

		taskFolderComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					okMethod();
				}
			}
		});
		taskFolderComboBox.setBounds(278, 46, 117, 27);

		taskFolderComboBox.setSelectedIndex(ToDoList.autoSelectedFolder);

		frame.getContentPane().add(taskFolderComboBox);

		JLabel taskFolderLabel = new JLabel("Folder");
		taskFolderLabel.setBounds(288, 18, 61, 16);
		frame.getContentPane().add(taskFolderLabel);

		JLabel importanceLabel = new JLabel("Level of Importance");
		importanceLabel.setBounds(20, 124, 130, 16);
		frame.getContentPane().add(importanceLabel);

		regularRadioButton = new JRadioButton("Regular");
		regularRadioButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					okMethod();
				}
			}
		});
		regularRadioButton.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		regularRadioButton.setSelected(true);
		buttonGroup.add(regularRadioButton);
		regularRadioButton.setBounds(20, 152, 141, 23);
		frame.getContentPane().add(regularRadioButton);

		highRadioButton = new JRadioButton("High");
		highRadioButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					okMethod();
				}
			}
		});
		highRadioButton.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		buttonGroup.add(highRadioButton);
		highRadioButton.setBounds(20, 176, 141, 23);
		frame.getContentPane().add(highRadioButton);

		errorMessage = new JLabel("Please enter text");
		errorMessage.setVisible(false);
		errorMessage.setForeground(new Color(255, 0, 0));
		errorMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		errorMessage.setBounds(66, 72, 106, 16);
		frame.getContentPane().add(errorMessage);

		JButton addTaskOkButton = new JButton("OK");
		addTaskOkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okMethod();
			}
		});
		addTaskOkButton.setBounds(278, 163, 117, 29);
		frame.getContentPane().add(addTaskOkButton);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		backButton.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		backButton.setBounds(6, 237, 50, 29);
		frame.getContentPane().add(backButton);

		noFolderErrorMessage = new JLabel("Please create a folder first!");
		noFolderErrorMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		noFolderErrorMessage.setVisible(false);
		noFolderErrorMessage.setForeground(Color.RED);
		noFolderErrorMessage.setBounds(44, 72, 172, 16);
		frame.getContentPane().add(noFolderErrorMessage);

		errorMessageChooseFolder = new JLabel("Please choose a folder!");
		errorMessageChooseFolder.setVisible(false);
		errorMessageChooseFolder.setForeground(Color.RED);
		errorMessageChooseFolder.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		errorMessageChooseFolder.setBounds(54, 72, 172, 16);
		frame.getContentPane().add(errorMessageChooseFolder);

	}

	private void okMethod() {
		errorMessage.setVisible(false);
		noFolderErrorMessage.setVisible(false);
		if (taskFolderComboBox.getItemCount() == 0) {
			noFolderErrorMessage.setVisible(true);
		} else if (userTaskName.getText().length() == 0) {
			errorMessage.setVisible(true);
		} else if (taskFolderComboBox.getSelectedIndex() == -1) {
			errorMessageChooseFolder.setVisible(true);
		} else {

			if (highRadioButton.isSelected()) {
				userTaskName.setText(userTaskName.getText() + "!!!");
			}
			ToDoList.addTask(userTaskName.getText(), taskFolderComboBox.getSelectedIndex());
			frame.setVisible(false);

		}
	}

}
