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
        Scanner ostream = new Scanner(sourceFile);
        List<String> readLines = new Vector<String>();
        while (ostream.hasNextLine()) {
            readLines.add(ostream.nextLine());
        }
        ostream.close();
        return readLines;
    }
    /* читаем все строки из файла по пути path и возвращаем этот список */
    public void writeAll(String path, List<String> lines) throws RuntimeException {
        File toWrite;
        FileWriter istream;
        try {
            toWrite = new File(path);
            istream = new FileWriter(toWrite, true);
        } catch (NullPointerException nullPath) {
            throw new RuntimeException("Path is null");
        } catch (IOException fileWriterException) {
             throw new RuntimeException("Unable to link writer");
        } catch (Exception otherException) {
            throw new RuntimeException("Something went wrong when initializing");
        }
        try {
            for (String line : lines) {
                istream.write(line);
                istream.append('\n');

            }
        } catch (IOException writingException) {
            throw new RuntimeException("Something went wrong when writing");
        }

    }
    /*пишем все строки из lines в файла по пути path */
    public void copy(String sourceFile, String destinationFile){}
    /*копируем файл по пути sourceFile в destinationFile*/
    public void delete(String path) {}
    /*удаляем файл по пути path*/


}
