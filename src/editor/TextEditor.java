package editor;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JFrame {
    private FileLoader fileLoader = new FileLoader();
    static JTextArea textArea = new JTextArea();
    static JTextField textField = new JTextField();



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

        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem exit = new JMenuItem("Exit");

        load.addActionListener( actionEvent -> textArea.setText( fileLoader.loadFile( textField.getText() ) ));
        save.addActionListener( actionEvent -> fileLoader.saveFile( textField.getText(), textArea.getText() ));
        exit.addActionListener( actionEvent -> dispose() );

        menu.add(save);
        menu.add(load);
        menu.addSeparator();
        menu.add(exit);

        menuBar.add(menu);

        return menuBar;
    }


    private JPanel createFileSelectGroup(){
        JButton load = new JButton("Load");
        JButton save = new JButton("Save");

        load.addActionListener( actionEvent -> textArea.setText( fileLoader.loadFile( textField.getText() ) ));
        save.addActionListener( actionEvent -> fileLoader.saveFile( textField.getText(), textArea.getText() ));

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

    private void createLayout(JComponent... argument) {
        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
                .addComponent(argument[0])
                .addComponent(argument[1])
                .addComponent(argument[2])
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(argument[0])
                .addComponent(argument[1])
                .addComponent(argument[2])
        );

        groupLayout.linkSize(1,argument[0], argument[1]);

        pack();
    }
}