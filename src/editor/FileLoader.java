package editor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class FileLoader {

    static String loadFile(String fileName)  {
        File file = new File(fileName);
        /*
           If file exists and not empty, read it
        */
        if (file.isFile()) {
            try {
               return Files.readString(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    static void saveFile(String fileName, String text, boolean usePrintLn) {
        /*
            If file name is empty
            user should select it
        */
        if (fileName == null) {
            fileName = FileChooser.save();
        }
        /*
          * if user selected nothing
        */
        if (fileName == null) {
            return;
        } else {
            /*
                Remember the file to work with
            */
            TextEditor.fileName = fileName;
        }

        File file = new File(fileName);
        /*
            If file not exists, create
        */
        if (!file.isFile()) {
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
                if (usePrintLn){
                    print.println(scText.nextLine());
                } else {
                    print.print(scText.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
