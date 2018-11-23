package editor;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JFrame {
    private FileLoader fileLoader = new FileLoader();
    static JTextArea textArea = new JTextArea();
    static JTextField textField = new JTextField();



    public TextEditor() {
        Dimension dimension = new Dimension(300,300);
        JPanel fileSelectGroup = createFileSelectGroup();
        JScrollPane scrollPane = new JScrollPane(textArea);

        createLayout(fileSelectGroup, scrollPane);

        setSize(dimension);
        setMinimumSize(dimension);
        setTitle("Text editor v 0.2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }


    private JPanel createFileSelectGroup(){

        JButton load = new JButton("Load");
        JButton save = new JButton("Save");

        load.addActionListener( actionEvent -> {
            textArea.setText( fileLoader.loadFile( textField.getText() ) );
        });
        save.addActionListener( actionEvent -> {
            fileLoader.saveFile( textField.getText(), textArea.getText());
        });

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
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(argument[0])
                .addComponent(argument[1])
        );

        pack();
    }
}