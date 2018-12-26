package editor.ui;

import editor.files.FileChooser;
import editor.files.FileLoader;
import editor.search.TextSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *  This is the base class of application, which creates UI and calling methods to work with documents
 */
public class TextEditor extends JFrame {

    /**
     * This is a working zone of application -text area for text typing and redacting, text field for searching
     * words, letters, regex in text. Use regex is a check box which controls searching mechanism
     */
    private static JTextArea textArea;
    private static JTextField textField;
    private static JCheckBox useRegex;

    /**
     *  That's common fields of UI to cast all elements to one style.
     */
    private Color backColor;
    private Font font;

    /**
     *  That's a file name which using for access to file to work with
     */
    private static String fileName;

    /**
     *  That's TextSearch class instance that implements searching inside of text
     */
    private TextSearch textSearch;

    /**
     * Class constructor that initialising and running an app
     */
    public TextEditor() {
        textArea = new JTextArea();
        textField = new JTextField();
        useRegex = new JCheckBox("Use regex");
        backColor = new Color(241, 241, 241);
        font = new Font(null,Font.BOLD,15);
        backColor = new Color(241, 241, 241);
        font = new Font(null,Font.BOLD,15);
        textSearch = new TextSearch();

        Dimension dimension = new Dimension(600,500);
        JMenuBar menu = createMenu();
        JPanel workingBar = createWorkingBar();
        JScrollPane scrollPane = new JScrollPane(textArea);
        addWindowListener(windowListener);
        createLayout(menu, workingBar, scrollPane);

        setSize(dimension);
        setMinimumSize(dimension);
        setTitle("Text editor v 1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Method creates a menu which contains file and searching zones
     * @return menu
     */
    private JMenuBar createMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu file = createFileMenu();
        JMenu search = createSearchingMenu();
        
        file.setFont(font);
        search.setFont(font);

        menuBar.add(file);
        menuBar.add(search);

        return menuBar;
    }

    /**
     * Method creates File section of menu
     * @return menuFile
     */
    private JMenu createFileMenu(){
        JMenu file = new JMenu("File");

        JMenuItem load = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save as");
        JMenuItem exit = new JMenuItem("Exit");

        load.addActionListener( actionEvent -> FileChooser.open() );

        save.addActionListener( actionEvent ->
                FileLoader.saveFile( fileName, textArea.getText(), true));

        saveAs.addActionListener( actionEvent ->
                FileLoader.saveFile( null, textArea.getText(), true));

        exit.addActionListener( actionEvent ->
                {
                    FileLoader.saveFile( fileName, textArea.getText(), true);
                    dispose();
                }
        );

        file.add(load);
        file.add(save);
        file.add(saveAs);
        file.addSeparator();
        file.add(exit);

        return file;
    }

    /**
     * Method creates Search section of menu
     * @return menuFile
     */
    private JMenu createSearchingMenu(){
        JMenu search = new JMenu("Search");

        JMenuItem start = new JMenuItem("Start search");
        JMenuItem prev = new JMenuItem("Previous match");
        JMenuItem next = new JMenuItem("Next match");
        JMenuItem useRegularex = new JMenuItem("Use regular expressions");


        useRegularex.addActionListener( actionEvent -> {
                useRegex.setSelected(!useRegex.isSelected());
                textSearch.search(TextSearch.FIND);
            }
        );
        start.addActionListener( actionEvent -> textSearch.search(TextSearch.FIND));
        next.addActionListener( actionEvent -> textSearch.search(TextSearch.NEXT));
        prev.addActionListener( actionEvent -> textSearch.search(TextSearch.PREV));

        search.add(start);
        search.add(prev);
        search.add(next);
        search.add(useRegularex);

        return search;
    }

    /**
     * Method creates a group of buttons and searching elements for work with application
     * @return JPanel with buttons, text field for searching and use regex checkbox
     */

    private JPanel createWorkingBar() {

        JPanel fileSelectGroup = new JPanel();

        GroupLayout groupLayout = new GroupLayout(fileSelectGroup);

        JButton load = configureButtons("img/open.png");
        JButton save = configureButtons("img/save.png");
        JButton find = configureButtons("img/find.png");
        JButton left = configureButtons("img/left.png");
        JButton right = configureButtons("img/right.png");
        useRegex.setFont(font);
        useRegex.setBackground(backColor);

        load.addActionListener( actionEvent -> FileChooser.open());
        save.addActionListener( actionEvent -> FileLoader.saveFile( fileName, textArea.getText(),true));
        find.addActionListener( actionEvent -> textSearch.search(TextSearch.FIND));
        right.addActionListener( actionEvent -> textSearch.search(TextSearch.NEXT));
        left.addActionListener( actionEvent -> textSearch.search(TextSearch.PREV));
        useRegex.addActionListener( actionEvent -> textSearch.search(TextSearch.FIND));

        textField.addActionListener( actionEvent -> textSearch.search(TextSearch.FIND));

        fileSelectGroup.setLayout(groupLayout);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                        .addComponent(save)
                        .addComponent(load)
                        .addComponent(textField)
                        .addComponent(find)
                        .addComponent(left)
                        .addComponent(right)
                        .addComponent(useRegex)

        );

        groupLayout.setVerticalGroup(groupLayout.createParallelGroup()
                        .addComponent(load)
                        .addComponent(save)
                        .addComponent(textField)
                        .addComponent(find)
                        .addComponent(left)
                        .addComponent(right)
                        .addComponent(useRegex)
        );

        groupLayout.linkSize(0, load, save, find, left, right);
        groupLayout.linkSize(1, load, save, find, left, right, useRegex);

        return fileSelectGroup;
    }

    /**
     * method creates a layout of application with menu, working panel and text area
     * @param menu
     * @param workingBar
     * @param scrollPane
     */
    private void createLayout(JComponent menu, JComponent workingBar, JComponent scrollPane) {
        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
                .addComponent(menu)
                .addComponent(workingBar)
                .addComponent(scrollPane)
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(menu)
                .addComponent(workingBar)
                .addComponent(scrollPane)
        );

        groupLayout.linkSize(1,menu, workingBar);

        pane.setBackground(backColor);
        pack();
    }

    /**
     * This method creates and configure button to common style
     * @param filename of icon
     * @return button
     */
    private JButton configureButtons(String filename) {
        JButton button = new JButton( new ImageIcon(filename));
        button.setPreferredSize(new Dimension(40,40));
        button.setBorderPainted(false);
        button.setBackground(backColor);
        return button;
    }

    /**
     * Getters and setters of class
     */
    public static JTextArea getTextArea() {
        return textArea;
    }

    public static JTextField getTextField() {
        return textField;
    }

    public static JCheckBox getUseRegex() {
        return useRegex;
    }

    public static void setFileName(String fileName) {
        TextEditor.fileName = fileName;
    }

    private WindowListener windowListener = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            FileLoader.saveFile( fileName, textArea.getText(), true);
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    };
}