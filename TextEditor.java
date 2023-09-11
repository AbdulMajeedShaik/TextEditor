import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    // JFrame to hold the main window of the text editor
    JFrame frame;

    // The menu bar at the top of the window
    JMenuBar menuBar;

    // Menu options for File and Edit
    JMenu file, edit;

    // File menu items
    JMenuItem open, save, newWindow;

    // Edit menu items
    JMenuItem cut, copy, paste, selectAll, close;

    // The area where the user can type and edit text
    JTextArea textArea;

    public TextEditor() {
        // Initialize the JFrame for the text editor
        frame = new JFrame("TextEditor(Abdul Majeed Shaik)");

        // Create the menu bar
        menuBar = new JMenuBar();

        // Create the File menu
        file = new JMenu("File");

        // Create the Edit menu
        edit = new JMenu("Edit");

        // Menu item to open a file
        open = new JMenuItem("Open File");

        // Menu item to save a file
        save = new JMenuItem("Save File");

        // Menu item to open a new window
        newWindow = new JMenuItem("New Window");

        // Add action listeners to the File menu items
        open.addActionListener(this);
        save.addActionListener(this);
        newWindow.addActionListener(this);

        // Menu item to cut selected text
        cut = new JMenuItem("Cut");

        // Menu item to copy selected text
        copy = new JMenuItem("Copy");

        // Menu item to paste copied/cut text
        paste = new JMenuItem("Paste");

        // Menu item to select all text
        selectAll = new JMenuItem("Select All");

        // Menu item to close the application
        close = new JMenuItem("Close");

        // Add action listeners to the Edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Add file menu items to the File menu
        file.add(open);
        file.add(save);
        file.add(newWindow);

        // Add edit menu items to the Edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add File and Edit menus to the menu bar
        menuBar.add(file);
        menuBar.add(edit);

        // Set the menu bar for the main window
        frame.setJMenuBar(menuBar);

        // Create a panel with an empty border and BorderLayout
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));

        // Create a text area where the user can edit text
        textArea = new JTextArea();

        // Create a JScrollPane to enable scrolling in the text area
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add the scroll pane with the text area to the panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add the panel with the text area and scroll pane to the main window
        frame.add(panel);

        // Set the position and size of the main window
        frame.setBounds(10, 10, 400, 400);

        // Make the main window visible
        frame.setVisible(true);

        // Use a null layout for custom component positioning
        frame.setLayout(null);

        // Close the application when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Create an instance of the TextEditor class
        TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle actions when menu items are clicked
        if (e.getSource() == cut) {
            textArea.cut(); // Perform cut operation
        }
        if (e.getSource() == copy) {
            textArea.copy(); // Perform copy operation
        }
        if (e.getSource() == paste) {
            textArea.paste(); // Perform paste operation
        }
        if (e.getSource() == selectAll) {
            textArea.selectAll(); // Select all text in the text area
        }
        if (e.getSource() == close) {
            System.exit(0); // Close the application when "Close" is selected
        }

        if(e.getSource() == open){
            // Create a file chooser dialog to open a file
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try{
                    // Read the selected file and load its content into the text area
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate, output = "";
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate + "\n";
                    }
                    textArea.setText(output);
                    bufferedReader.close(); // Close the BufferedReader
                }
                catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(e.getSource()==save){
            // Create a file chooser dialog to save a file
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    // Write the content of the text area to the selected file
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close(); // Close the BufferedWriter
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(e.getSource() == newWindow){
            // Create a new instance of the TextEditor class to open a new window
            TextEditor newTextEditor = new TextEditor();
        }
    }
}
