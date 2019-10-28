package ru.mipt.collections;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class FileUtils {
    public List<String> readAll(String path) throws RuntimeException {
        FileReader sourceFile;
        try {
            sourceFile = new FileReader(path);
        } catch (FileNotFoundException noFile) {
            throw new RuntimeException("Invalid pathname");
        } catch (Exception otherException) {
            throw new RuntimeException("Something went wrong when trying to read from file");
        }
        Scanner istream = new Scanner(sourceFile);
        List<String> readLines = new Vector<String>();
        while (istream.hasNextLine()) {
            readLines.add(istream.nextLine());
        }
        istream.close();
        return readLines;
    }
    /* читаем все строки из файла по пути path и возвращаем этот список */
    public void writeAll(String path, List<String> lines) throws RuntimeException {
        File toWrite;
        FileWriter ostream;
        try {
            toWrite = new File(path);
            ostream = new FileWriter(toWrite, true);
        } catch (NullPointerException nullPath) {
            throw new RuntimeException("Path is null");
        } catch (IOException thrownByWriter) {
             throw new RuntimeException("Unable to link writer: " + thrownByWriter.getMessage());
        } catch (Exception otherException) {
            throw new RuntimeException("Something went wrong when initializing");
        }
        try {
            for (String line : lines) {
                ostream.write(line);
                ostream.append('\n');
            }
            ostream.flush();
            ostream.close();
        } catch (IOException writingException) {
            throw new RuntimeException("Something went wrong when writing: " + writingException.getMessage());
        }

    }
    /*пишем все строки из lines в файла по пути path */
    public void copy(String sourceFile, String destinationFile) throws RuntimeException {
        try {
            FileReader toRead = new FileReader(sourceFile);
            FileWriter toWrite = new FileWriter(destinationFile, true);
            Scanner readFrom = new Scanner(toRead);
            while (readFrom.hasNextLine()) {
                toWrite.write(readFrom.nextLine());
            }
        } catch (FileNotFoundException thrownByReader) {
            throw new RuntimeException("Something is wrong with the source file: " + thrownByReader.getMessage());
        } catch (IOException thrownByWriter) {
            throw new RuntimeException("Something is wrong with the destination file: " + thrownByWriter.getMessage());
        }
    }
    /*копируем файл по пути sourceFile в destinationFile*/
    public void delete(String path) {
        File toDelete;
        try {
            toDelete = new File(path);
        } catch (NullPointerException thrownByConstructor) {
            throw new RuntimeException("Path is null: unable to open the file");
        }
        if (!toDelete.delete()) {
            throw new RuntimeException("Unable to delete the file");
        }
    }
    /*удаляем файл по пути path*/


}
