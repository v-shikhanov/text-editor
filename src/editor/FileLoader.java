package editor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class FileLoader {


    String loadFile(String fileName)  {
        File file = new File(fileName);

        /*
            If file exists and not empty, read it
         */
        if(file.isFile()) {
            try {
               return Files.readString(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
                return "File is Empty!";
            }
        } else {
            return "File Not Found!";
        }
    }

    void saveFile(String fileName, String text) {


        Scanner scName = new Scanner(fileName);

        /*
            If file name is empty
            use default one
        */
        if( !scName.hasNext()) {
            fileName = "noname.txt";
            TextEditor.textField.setText(fileName);
        }



        File file = new File(fileName);

        /*
            If file not exists, create
        */
        if(!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /*
            Save the data
        */
        try {
            PrintStream print = new PrintStream(file);
            Scanner scText = new Scanner(text);
            while (scText.hasNext()){
                print.println(scText.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}
