package com.example.TPXProj.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Read and deliver files. Take care of errors as smoothly as possible.
 *
 * @author Caleb Warner
 */
public class FileReader {
    // *************************************************************************
    // Constants
    // *************************************************************************
    /** Reference to the database object that communicates with the ClearDB MySQL Database. */
    private static final String fileNotFoundPath = "src/main/webapp/file-not-found.html";


    // *************************************************************************
    // Public Methods
    // *************************************************************************
    /**
     * Attempt to read and return file that is requested. If not found, attempt to read and return
     * file-not-found.html. If that is not found, return canned message.
     *
     * @param filepath File requested to deliver in String format
     * @return String representation of entire file (or canned message if failure)
     */
    public static String readFile(String filepath) {
        StringBuilder fileString = new StringBuilder();
        try {
            // Try to deliver requested file
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                fileString.append(scanner.nextLine());
            } // while

            return fileString.toString();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
            try {
                // If failure (file not found), try to deliver fileNotFound html file
                File fileNotFound = new File(fileNotFoundPath);
                Scanner scanner = new Scanner(fileNotFound);

                while (scanner.hasNextLine()) {
                    fileString.append(scanner.nextLine());
                } // while

                return fileString.toString();
            } catch (FileNotFoundException eNotFound) {
                // If both fail, print both stack traces and return message
                e.printStackTrace();
                eNotFound.printStackTrace();

                return "File could not be found.";
            } // try-catch
        } // try-catch
    } // public static String readFile(
} // public class FileReader