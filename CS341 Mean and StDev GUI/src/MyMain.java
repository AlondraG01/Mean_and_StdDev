import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

public class MyMain {

	// SPECIFY THE APPLICATION ELEMENTS: UI AND OBJECTS
	private static JButton inputBtn;
	private static JButton outputBtn;
	private static JButton computeBtn;
	private static JFrame jframeWindow;
	private static JPanel panel;
	private static File fileToRead;
	private static File fileToSave;
	public static LinkedList<Double> list;

	public static void main(String[] args) {
		// create GUI application window
		constructAppWindow();
		addListenerEvents();
	}

	private static void constructAppWindow() {
		jframeWindow = new JFrame();
		jframeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// construct a panel container to store buttons, etc.
		panel = new JPanel(new GridLayout(3, 0)); // 3 ROWS NO COLUMNS
		panel.setPreferredSize(new Dimension(500, 150));
		panel.setBackground(Color.DARK_GRAY);

		// build buttons, etc. and add them to the panel
		inputBtn = new JButton("Specify Input Text File");
		outputBtn = new JButton("Specify Output Text File");
		computeBtn = new JButton("Perform Work");
		panel.add(inputBtn);
		panel.add(outputBtn);
		panel.add(computeBtn);

		// add panel to the application window
		jframeWindow.add(panel);

		// TASK 5: MAKE THE APPLICATION WINDOW VISIBLE TO THE USER
		jframeWindow.pack();
		jframeWindow.setVisible(true);
	}

	private static void addListenerEvents() {
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestInputFile();
			}
		});
		outputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestSaveFile();
			}
		});
		computeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					computeSomething();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	// DISPLAYS THE OUTPUT TEXT FILE
	public static void requestSaveFile() {
		// parent component of the dialog
		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToSave = fileChooser.getSelectedFile();
			// output the directory where the output file is at
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		}
	}

	// GETS THE INPUT TEXT FILE
	public static void requestInputFile() {
		// linked list
		list = new LinkedList<Double>();

		// parent component of the dialog
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			// fileToRead is where the text file is stored in
			fileToRead = jfc.getSelectedFile();

			// prints out the directory of the file
			System.out.println(fileToRead.getAbsolutePath());
		}

		// reads the file and puts integers into int linked list
		try {
			Scanner input = new Scanner(new File(fileToRead.getAbsolutePath()));
			// place numbers into string
			String str = "";

			// goes through the text lines
			while (input.hasNext()) {
				str += input.next() + " ";
			}
			input.close();

			// check to make sure the string isn't empty
			if (str.isEmpty()) {
				System.out.println("Error: There is no input");
			}
			// check to make sure there are only numbers in the input
			if (checkForLetters(str) == false) {
				System.out.println("Error: Only real numbers can be input");
			}

			// traverse through string and push the numbers into a linked list
			String[] realNums = str.split(" ");
			// populate elements from string array to int array
			int[] values = new int[realNums.length];
			for (int i = 0; i < realNums.length; i++) {
				values[i] = Integer.parseInt(realNums[i]);
			}

			// pushes into the linked list
			for (int i = 0; i < values.length; i++) {
				list.push(values[i]);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		}
	}

	// checks to make sure there is only numbers in the input
	public static boolean checkForLetters(String str) {
		str = str.replaceAll("\\s", "");
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	// COMPUTES THE STANDARD DEVIATION
	public static void computeSomething() throws FileNotFoundException, IOException {
		System.out.println("now computing");
		
		//PrintStream allows you to grab the file you have chosen
		PrintStream out = new PrintStream(new FileOutputStream(fileToSave));
		//System.setOut will write whatever you are trying to output into the file you have chosen
		System.setOut(out);
		
		//calculating the standard deviation
		int length = LinkedList.countNodes(list);
		int sum = LinkedList.sum(list);
		double mean = (double)sum / (double)length;
		double stdDev = LinkedList.standardDeviation(list, mean);
		
		stdDev = Math.sqrt(stdDev/ length);
	
		//LinkedList.printList(list);
		System.out.println("Mean: \n" + mean);
		System.out.println("Std Dev: \n" + stdDev);
	}

}