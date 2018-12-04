package editor;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JFrame {
    static JTextArea textArea = new JTextArea();
    static JTextField textField = new JTextField();
    static String fileName;


    public TextEditor() {
        Dimension dimension = new Dimension(500,500);

        JMenuBar menu = menuCreator();
        JPanel fileSelectGroup = createFileSelectGroup();
        JScrollPane scrollPane = new JScrollPane(textArea);

        createLayout(menu , fileSelectGroup, scrollPane);

        setSize(dimension);
        setMinimumSize(dimension);
        setTitle("Text editor v 0.3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JMenuBar menuCreator(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem load = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save as");
        JMenuItem exit = new JMenuItem("Exit");

        load.addActionListener( actionEvent -> FileChooser.open() );
        save.addActionListener( actionEvent -> FileLoader.saveFile( fileName, textArea.getText(), true));
        saveAs.addActionListener( actionEvent -> FileLoader.saveFile( null, textArea.getText(), true));
        exit.addActionListener( actionEvent -> dispose() );

        menu.add(load);
        menu.add(save);
        menu.add(saveAs);
        menu.addSeparator();
        menu.add(exit);

        menuBar.add(menu);

        return menuBar;
    }


    private JPanel createFileSelectGroup(){
        JButton load = new JButton("Open");
        JButton save = new JButton("Save");

        load.addActionListener( actionEvent -> FileChooser.open());
        save.addActionListener( actionEvent -> FileLoader.saveFile( fileName, textArea.getText(),true));

        return formFileSelectGroup(textField,load,save);
    }


    private JPanel formFileSelectGroup(JTextField textField, JButton load, JButton save) {
        JPanel fileSelectGroup = new JPanel();

        GroupLayout groupLayout = new GroupLayout(fileSelectGroup);

        fileSelectGroup.setLayout(groupLayout);


        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                        .addComponent(textField)
                        .addGap(5)
                        .addComponent(save)
                        .addGap(5)
                        .addComponent(load)
        );

        groupLayout.setVerticalGroup(groupLayout.createParallelGroup()
                        .addComponent(textField)
                        .addComponent(load)
                        .addComponent(save)
        );

        groupLayout.linkSize(1,textField, load, save);

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

        pack();
    }
}