package editor;

import javax.swing.*;

public class TextEditor extends JFrame {

    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TextEditor();
    }

}