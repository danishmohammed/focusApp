import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;

public class MainScreen {

	private JFrame frame;
	private JTextField timerTextField;
	String timeString;
	static JScrollPane scrollPane = new JScrollPane();
	static JTree tree;
	static boolean addTaskToFolder = false;
	DefaultTreeModel model;
	MutableTreeNode nodeToRemove;
	MutableTreeNode nodeToRemoveParent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
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
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// try catch for getting the file; if its the users first time, it will create a
		// file
		tree = new JTree();
		ToDoList.uploadFile();
		ToDoList.fileToVector();
		ToDoList.updateTree();

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		JPanel allTasks = new JPanel();
		JPanel timer = new JPanel();

		tabbedPane.addTab("All Tasks", null, allTasks, "show tasks in progress");
		tabbedPane.addTab("Timer", null, timer, "go to timer");
		timer.setLayout(null);

		JCheckBox soundCheckBox = new JCheckBox("Beep when done");
		soundCheckBox.setSelected(true);
		soundCheckBox.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		soundCheckBox.setBounds(6, 6, 155, 23);
		timer.add(soundCheckBox);

		JButton set10Minutes = new JButton("10");
		set10Minutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerTextField.setText("10:00");
			}
		});
		set10Minutes.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		set10Minutes.setBounds(329, 55, 45, 29);
		timer.add(set10Minutes);

		JButton set15Minutes = new JButton("15");
		set15Minutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerTextField.setText("15:00");
			}
		});
		set15Minutes.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		set15Minutes.setBounds(329, 86, 45, 29);
		timer.add(set15Minutes);

		JButton set20Minutes = new JButton("20");
		set20Minutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerTextField.setText("20:00");
			}
		});
		set20Minutes.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		set20Minutes.setBounds(329, 115, 45, 29);
		timer.add(set20Minutes);

		JButton set30Minutes = new JButton("30");
		set30Minutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerTextField.setText("30:00");
			}
		});
		set30Minutes.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		set30Minutes.setBounds(329, 146, 45, 29);
		timer.add(set30Minutes);

		JButton set60Minutes = new JButton("60");
		set60Minutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerTextField.setText("60:00");
			}
		});
		set60Minutes.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		set60Minutes.setBounds(329, 175, 45, 29);
		timer.add(set60Minutes);

		JLabel labelSetMinutes = new JLabel("Time");
		labelSetMinutes.setBounds(336, 32, 87, 16);
		timer.add(labelSetMinutes);

		JButton timerStop = new JButton("Stop");
		timerStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeString = "00:00";
			}
		});
		timerStop.setBackground(Color.LIGHT_GRAY);
		timerStop.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		timerStop.setBounds(145, 159, 65, 29);
		timer.add(timerStop);

		timerTextField = new JTextField();

		JLabel timerErrorMessage = new JLabel("Please enter a time in the following format: XX:XX");
		timerErrorMessage.setVisible(false);
		timerErrorMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		timerErrorMessage.setForeground(Color.RED);
		timerErrorMessage.setBounds(62, 204, 312, 16);
		timer.add(timerErrorMessage);

		JButton timerStart = new JButton("Start");
		timerStart.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		timerStart.setBounds(226, 159, 65, 29);
		timer.add(timerStart);
		timerStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set10Minutes.setVisible(false);
				set15Minutes.setVisible(false);
				set20Minutes.setVisible(false);
				set30Minutes.setVisible(false);
				set60Minutes.setVisible(false);
				labelSetMinutes.setVisible(false);

				timerStart.setEnabled(false);
				timerErrorMessage.setVisible(false);
				timerTextField.setEditable(false);
				timeString = timerTextField.getText();
				if (timeString.charAt(2) == ':' && Character.isDigit(timeString.charAt(0))
						&& Character.isDigit(timeString.charAt(1)) && Character.isDigit(timeString.charAt(3))
						&& Character.isDigit(timeString.charAt(4))) {
					Integer[] time = { Integer.parseInt(Character.toString(timeString.charAt(0))),
							Integer.parseInt(Character.toString(timeString.charAt(1))),
							Integer.parseInt(Character.toString(timeString.charAt(3))),
							Integer.parseInt(Character.toString(timeString.charAt(4))) };

					Thread thread1 = new Thread() {
						public void run() {
							while (!timeString.equals("00:00")) {
								if (time[3] == 0) {
									if (time[2] == 0) {
										if (time[1] == 0) {
											time[3] = 9;
											time[2] = 5;
											time[1] = 9;
											time[0] -= 1;
										} else {
											time[3] = 9;
											time[2] = 5;
											time[1] -= 1;
										}
									} else {
										time[3] = 9;
										time[2] -= 1;
									}
								} else {
									time[3] -= 1;
								}
								// update the screen
								String updateText = time[0].toString() + time[1].toString() + ":" + time[2].toString()
										+ time[3].toString();
								timeString = updateText;
								timerTextField.setText(updateText);
								// wait a second
								if (!timerTextField.getText().equals("00:00")) {
									try {
										Thread.sleep(1000);

									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}

							}
							if (soundCheckBox.isSelected()) {
								Toolkit.getDefaultToolkit().beep();
							}
							timerStart.setEnabled(true);
							timerTextField.setEditable(true);
							timerTextField.setBackground(UIManager.getColor("PasswordField.selectionBackground"));
							set10Minutes.setVisible(true);
							set15Minutes.setVisible(true);
							set20Minutes.setVisible(true);
							set30Minutes.setVisible(true);
							set60Minutes.setVisible(true);
							labelSetMinutes.setVisible(true);
							tabbedPane.setSelectedIndex(1);
							// progressBar.setVisible(false);
						}
					};
					thread1.start();
				} else {
					timerErrorMessage.setVisible(true);
					Toolkit.getDefaultToolkit().beep();
					timerStart.setEnabled(true);
					timerTextField.setEditable(true);
					timerTextField.setBackground(UIManager.getColor("PasswordField.selectionBackground"));
					set10Minutes.setVisible(true);
					set15Minutes.setVisible(true);
					set20Minutes.setVisible(true);
					set30Minutes.setVisible(true);
					set60Minutes.setVisible(true);
					labelSetMinutes.setVisible(true);
				}

			}
		});

		timerTextField.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		timerTextField.setHorizontalAlignment(SwingConstants.CENTER);
		timerTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		timerTextField.setText("10:00");
		timerTextField.setBackground(UIManager.getColor("PasswordField.selectionBackground"));
		timerTextField.setBounds(112, 32, 208, 173);
		timer.add(timerTextField);
		timerTextField.setColumns(10);

//========================All Tasks Code==================================

		allTasks.setLayout(new BorderLayout(0, 0));
		allTasks.add(scrollPane);

		tree.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		tree.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if ((e.getKeyChar() == 'd' || e.getKeyChar() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyChar() == KeyEvent.VK_ENTER) && tree.getSelectionPath() != null) {
					TreePath myPath = tree.getSelectionPath();
					model = (DefaultTreeModel) tree.getModel();
					nodeToRemove = (MutableTreeNode) myPath.getLastPathComponent();

					try {
						nodeToRemoveParent = (MutableTreeNode) myPath.getParentPath().getLastPathComponent();
						int indexOfRemovedItem = nodeToRemoveParent.getIndex(nodeToRemove);
						// System.out.println(indexOfRemovedItem);

						try {
							int indexOfFolder = (((MutableTreeNode) myPath.getParentPath().getParentPath()
									.getLastPathComponent()).getIndex(nodeToRemoveParent));
							ToDoList.deleteNode(indexOfRemovedItem, indexOfFolder);
						} catch (Exception ex) {
							ToDoList.deleteNode(indexOfRemovedItem);
						}
						model.removeNodeFromParent(nodeToRemove);

					} catch (Exception exc) {

					}
				}
			}
		});

		scrollPane.setViewportView(tree);

		JPanel addTaskAndFolderPanel = new JPanel();
		scrollPane.setColumnHeaderView(addTaskAndFolderPanel);
		
		JButton addTaskButton = new JButton("Add Task");
		addTaskButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 't') {
					AddTask.main(null);
				} else if (e.getKeyChar() == 'f') {
					AddFolder.main(null);
				}
			}
		});
		addTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (AddTask.taskFolderComboBox.getItemCount() == 0) {
					addTaskToFolder = true;
					AddFolder.main(null);
				} else {
				AddTask.main(null);
				}
			}
		});
		addTaskAndFolderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		addTaskAndFolderPanel.add(addTaskButton);

		JButton addFolderButton = new JButton("Add Folder");
		addFolderButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 't') {
					AddTask.main(null);
				} else if (e.getKeyChar() == 'f') {
					AddFolder.main(null);
				}
			}
		});
		addFolderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFolder.main(null);
			}
		});

		addTaskAndFolderPanel.add(addFolderButton);

		frame.getContentPane().add(tabbedPane);

	}
}
