/***
 * 
 * Author: Jonathan O'Berry
 * Date: 2021-04-21
 * Project Title: Text Editor
 *
 ***/

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

public class textEditor extends JFrame implements ActionListener{

		JFrame topFrame = null;

		JTextArea outputArea = null;

	/**
	* This is the constructor for textEditor, it contains almost everything for the formatting of the text editor.
	* 
	*/

	textEditor() {

		JMenu menuOne = null; 

		JMenu menuTwo = null; 

		GridBagConstraints layoutConst = null;

		outputArea = new JTextArea(); 

		outputArea.setPreferredSize(new Dimension(500, 500));

		topFrame = new JFrame ("O'Berry Text Editor");

		topFrame.setLayout(new GridBagLayout());

		layoutConst = new GridBagConstraints();

		JMenuBar barMenu = new JMenuBar();

		layoutConst.gridx = 0;

		layoutConst.gridy = 0;

		layoutConst.insets = new Insets(10, 10, 10, 10);

		topFrame.add(outputArea, layoutConst);

		menuOne = new JMenu("File");

		JMenuItem itemOne   = new JMenuItem("New");

		JMenuItem itemTwo   = new JMenuItem("Save");

		JMenuItem itemThree = new JMenuItem("Print");

		JMenuItem itemFour  = new JMenuItem("Open");

		//These Action Listeners connect the buttons to what they should do.

		itemOne.addActionListener(this);
		itemTwo.addActionListener(this);
		itemThree.addActionListener(this);
		itemFour.addActionListener(this);

		//This adds all of the items to the sub menu under file.

		menuOne.add(itemOne);

		menuOne.add(itemTwo);

		menuOne.add(itemThree);

		menuOne.add(itemFour);

		menuTwo = new JMenu("Edit"); 

		JMenuItem itemFive  = new JMenuItem("Copy");

		JMenuItem itemSix   = new JMenuItem("Cut");

		JMenuItem itemSeven = new JMenuItem("Paste");

		//These Action Listeners connect the buttons to what they should do.

		itemFive.addActionListener(this);
		itemSix.addActionListener(this);
		itemSeven.addActionListener(this);

		//These add the items to the submenu under Edit

		menuTwo.add(itemFive);

		menuTwo.add(itemSix);

		menuTwo.add(itemSeven);
		
		JMenuItem closingMenu = new JMenuItem("Close"); 

		closingMenu.addActionListener(this);

		//This adds the menu bar at the top of the window.

		barMenu.add(menuOne);
		
		barMenu.add(menuTwo);

		barMenu.add(closingMenu);

		topFrame.setJMenuBar(barMenu);

		topFrame.pack();

		topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		topFrame.setVisible(true);
	}

	/**
	* actionPerformed is a method that comes from ActionListener which is implemented.
	* The if statements perform the actual action being done when an option in one of the menus is selected.
	*/

	public void actionPerformed(ActionEvent e) {

		String stringName = e.getActionCommand();

		if(stringName.equals("Copy")) {

			outputArea.copy();

		} else if (stringName.equals("Cut")) {

			outputArea.cut();

		} else if (stringName.equals("Paste")) {

			outputArea.paste();

		} else if (stringName.equals("Save")) {

			JFileChooser filePick = new JFileChooser("f:");

			if (filePick.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

				File userFile = new File(filePick.getSelectedFile().getAbsolutePath());

				try { 

					FileWriter fileWrite = new FileWriter (userFile, false); 

					BufferedWriter bw = new BufferedWriter (fileWrite); 

					bw.write(outputArea.getText()); 

					bw.flush();
					bw.close(); 
				} 
				catch (Exception ex) {

					JOptionPane.showMessageDialog(topFrame, ex.getMessage());

				}

			}
			else {

				JOptionPane.showMessageDialog(topFrame, "The process has been cancelled");

			} 
		} else if (stringName.equals("Print")) {

			try {

				outputArea.print();

			}
			catch (Exception ex) {

				JOptionPane.showMessageDialog(topFrame, ex.getMessage());

			}

		} else if (stringName.equals("Open")) {

			JFileChooser filePick = new JFileChooser("f:");

			if (filePick.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			
				File userFile = new File(filePick.getSelectedFile().getAbsolutePath());

				try { 

					String stringOne = "";
					String stringTwo = "";

					FileReader readFile = new FileReader(userFile);

					BufferedReader br = new BufferedReader(readFile);

					stringOne = br.readLine(); 

					while ((stringTwo = br.readLine()) != null) {

						stringOne = stringOne + "\n" + stringTwo;

					} 

					outputArea.setText(stringOne);

				} 
				catch (Exception ex) {

					JOptionPane.showMessageDialog(topFrame, "The process has been cancelled");

				}
	

			} else {

				JOptionPane.showMessageDialog(topFrame, "The process has been cancelled");

			}

		} else if (stringName.equals("New")) {

			outputArea.setText("");

		} else if (stringName.equals("Close")) {

			System.exit(0);

		}

	}

	public static void main(String args[]) {

		textEditor textStuff = new textEditor();

	}

}