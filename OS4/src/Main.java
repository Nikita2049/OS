import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class Main {

	private JFrame frame;
	public static int diskSize = 500;
	static int sectorSize = 10;
	Disk disk;
	private JTextField tFName;
	private JTextField tFSize;
	private Map<DefaultMutableTreeNode, File> dict;
	JTree jTree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
		Main();
	}

	private void Main() {
		disk = new Disk(diskSize / sectorSize);
		dict = new HashMap<DefaultMutableTreeNode, File>();
	}

	public void addNewItem(File f, Object top) {
		DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
		Object obj = top;
		if (obj != null) {
			DefaultMutableTreeNode sel = (DefaultMutableTreeNode) obj;
			if (sel.isRoot()) {
				DefaultMutableTreeNode tmp = new DefaultMutableTreeNode(
						f.getName() + "-" + f.getSize());

				model.insertNodeInto(tmp, sel, sel.getChildCount());
				dict.put(tmp, f);
			}
			jTree.expandPath(new TreePath(sel.getPath()));
		}

	}

	public void removeItem() {
		DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
		Object obj = jTree.getLastSelectedPathComponent();
		if (obj != null) {
			DefaultMutableTreeNode sel = (DefaultMutableTreeNode) obj;
			if (!sel.isRoot()) {
				dict.remove(sel);
				model.removeNodeFromParent(sel);
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 852, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("ROOT");
		jTree = new JTree(top);
		jTree.setShowsRootHandles(true);
		jTree.setBounds(632, 13, 190, 368);
		frame.getContentPane().add(jTree);

		Panel panel = new Panel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 400, 200);
		frame.getContentPane().add(panel);

		JButton btnCreateFile = new JButton("CreateFile");
		btnCreateFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String name = tFName.getText();
					int size = Integer.valueOf(tFSize.getText());
					if (name != "" && size > 0) {

						File f = new File(name, size);
						addNewItem(f, top);
						tFName.setText("");
						tFSize.setText("");
					} else {
						throw new Exception("Заполните все поля");
					}panel.repaint();
				} catch(ExFull exf){
					JOptionPane.showMessageDialog(null, exf.getMessage());
					System.exit(0);
				}
				catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnCreateFile.setBounds(232, 318, 119, 25);
		frame.getContentPane().add(btnCreateFile);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
				Object obj = jTree.getLastSelectedPathComponent();
				if (obj != null) {
					DefaultMutableTreeNode sel = (DefaultMutableTreeNode) obj;
					File f = dict.get(sel);
					f.delete();
				}
				panel.repaint();
				removeItem();

			}
		});
		btnDelete.setBounds(363, 318, 119, 25);
		frame.getContentPane().add(btnDelete);

		JButton btnCreatecatalog = new JButton("CreateCatalog");
		btnCreatecatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String name = tFName.getText();
					//int size = Integer.valueOf(tFSize.getText());
					if (name != "") {

						File f = new File(name, 1, true);
						addNewItem(f, top);
						tFName.setText("");
						tFSize.setText("");
					} else {
						throw new Exception("Заполните все поля");
					}panel.repaint();
				} catch(ExFull exf){
					JOptionPane.showMessageDialog(null, exf.getMessage());
					System.exit(0);
				}
				catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				panel.repaint();
			}
		});
		btnCreatecatalog.setBounds(232, 356, 119, 25);
		frame.getContentPane().add(btnCreatecatalog);

		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultTreeModel model = (DefaultTreeModel) jTree
							.getModel();
					Object obj = jTree.getLastSelectedPathComponent();
					if (obj != null) {
						DefaultMutableTreeNode sel = (DefaultMutableTreeNode) obj;
						File f = dict.get(sel);
						File f2 = new File(f.getName(), f.getSize());
						addNewItem(f2, top);
					}
					if (obj != null) {
						DefaultMutableTreeNode sel = (DefaultMutableTreeNode) obj;
						File f = dict.get(sel);
						f.delete();
					}
					panel.repaint();
					removeItem();
				} catch(ExFull exf){
					JOptionPane.showMessageDialog(null, exf.getMessage());
					System.exit(0);
				}
				catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnMove.setBounds(363, 356, 119, 25);
		frame.getContentPane().add(btnMove);

		JButton btnCopy = new JButton("Copy");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultTreeModel model = (DefaultTreeModel) jTree
							.getModel();
					Object obj = jTree.getLastSelectedPathComponent();
					if (obj != null) {
						DefaultMutableTreeNode sel = (DefaultMutableTreeNode) obj;
						File f = dict.get(sel);
						File f2 = new File(f.getName(), f.getSize());
						addNewItem(f2, top);
					}
					panel.repaint();
				} catch(ExFull exf){
					JOptionPane.showMessageDialog(null, exf.getMessage());
					System.exit(0);
				}
				catch (Exception ex) {
					
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnCopy.setBounds(511, 318, 97, 25);
		frame.getContentPane().add(btnCopy);

		tFName = new JTextField();
		tFName.setBounds(104, 319, 116, 22);
		frame.getContentPane().add(tFName);
		tFName.setColumns(10);

		JLabel lblNewLabel = new JLabel("name");
		lblNewLabel.setBounds(22, 319, 56, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("size");
		lblNewLabel_1.setBounds(22, 365, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);

		tFSize = new JTextField();
		tFSize.setBounds(104, 359, 116, 22);
		frame.getContentPane().add(tFSize);
		tFSize.setColumns(10);

		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
				Object obj = jTree.getLastSelectedPathComponent();
				if (obj != null) {
					DefaultMutableTreeNode sel = (DefaultMutableTreeNode) obj;
					File f = dict.get(sel);
					f.choose();
				}
				panel.repaint();
			}
		});
		btnChoose.setBounds(511, 356, 97, 25);
		frame.getContentPane().add(btnChoose);
	}
}
