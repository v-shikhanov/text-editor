package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TextEditor extends JFrame {
    static JTextArea textArea = new JTextArea();
    static JTextField textField = new JTextField();
    static String fileName;
    static JCheckBox useRegex = new JCheckBox("Use regex");

    private Color backColor = new Color(241, 241, 241);
    private Font font = new Font(null,Font.BOLD,15);

   // private Thread searchThread = new TextSearch();
    TextSearch textSearch = new TextSearch();

    public TextEditor() {
        Dimension dimension = new Dimension(600,500);

        JMenuBar menu = menuCreator();
        JPanel fileSelectGroup = createFileSelectGroup();
        JScrollPane scrollPane = new JScrollPane(textArea);
        addWindowListener(windowListener);
        createLayout(menu, fileSelectGroup, scrollPane);

        setSize(dimension);
        setMinimumSize(dimension);
        setTitle("Text editor v 0.5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JMenuBar menuCreator(){
        JMenuBar menuBar = new JMenuBar();
        JMenu file = menuFileCreator();
        JMenu search = menuSearchCreator();
        
        file.setFont(font);
        search.setFont(font);

        menuBar.add(file);
        menuBar.add(search);

        return menuBar;
    }

    private JMenu menuFileCreator(){
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

    private JMenu menuSearchCreator(){
        JMenu search = new JMenu("Search");

        JMenuItem start = new JMenuItem("Start search");
        JMenuItem prev = new JMenuItem("Previous match");
        JMenuItem next = new JMenuItem("Next match");
        JMenuItem useRegularex = new JMenuItem("Use regular expressions");


        useRegularex.addActionListener( actionEvent -> {
                useRegex.setSelected(!useRegex.isSelected());
                textSearch.search();
            }
        );
        start.addActionListener( actionEvent -> textSearch.search());
        next.addActionListener( actionEvent -> textSearch.nextMatch());
        prev.addActionListener( actionEvent -> textSearch.prevMatch());

        search.add(start);
        search.add(prev);
        search.add(next);
        search.add(useRegularex);

        return search;
    }

    private JPanel createFileSelectGroup(){
        ImageIcon saveIcon = new ImageIcon("img/save.png");
        ImageIcon openIcon = new ImageIcon("img/open.png");
        ImageIcon findIcon = new ImageIcon("img/find.png");
        ImageIcon leftIcon = new ImageIcon("img/left.png");
        ImageIcon rightIcon = new ImageIcon("img/right.png");

        JButton load = new JButton(openIcon);
        JButton save = new JButton(saveIcon);
        JButton find = new JButton(findIcon);
        JButton left = new JButton(leftIcon);
        JButton right = new JButton(rightIcon);

        load.setPreferredSize(new Dimension(40,40));
        save.setPreferredSize(new Dimension(40,40));
        find.setPreferredSize(new Dimension(40,40));
        left.setPreferredSize(new Dimension(40,40));
        right.setPreferredSize(new Dimension(40,40));
        useRegex.setFont(font);

        load.setBorderPainted(false);
        save.setBorderPainted(false);
        find.setBorderPainted(false);
        left.setBorderPainted(false);
        right.setBorderPainted(false);

        load.setBackground(backColor);
        save.setBackground(backColor);
        find.setBackground(backColor);
        left.setBackground(backColor);
        right.setBackground(backColor);
        useRegex.setBackground(backColor);

        load.addActionListener( actionEvent -> FileChooser.open());
        save.addActionListener( actionEvent -> FileLoader.saveFile( fileName, textArea.getText(),true));
        find.addActionListener( actionEvent -> textSearch.search());
        right.addActionListener( actionEvent -> textSearch.nextMatch());
        left.addActionListener( actionEvent -> textSearch.prevMatch());
        useRegex.addActionListener( actionEvent -> textSearch.search());

        return formFileSelectGroup(textField, load, save, find, left, right, useRegex);
    }

    private JPanel formFileSelectGroup(JTextField textField, JButton load, JButton save, JButton find,
                                       JButton left, JButton right, JCheckBox useRegex) {

        JPanel fileSelectGroup = new JPanel();

        GroupLayout groupLayout = new GroupLayout(fileSelectGroup);

        textField.addActionListener( actionEvent -> textSearch.search());

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

    private void createLayout(JComponent menu ,JComponent fileSelectGroup,JComponent scrollPane) {
        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
                .addComponent(menu)
                .addComponent(fileSelectGroup)
                .addComponent(scrollPane)
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(menu)
                .addComponent(fileSelectGroup)
                .addComponent(scrollPane)
        );

        groupLayout.linkSize(1,menu, fileSelectGroup);

        pane.setBackground(backColor);
        pack();
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