package ru.mipt.collections;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.System.lineSeparator;
import static java.lang.System.setOut;

public class FileUtils {
    public static List<String> readAll(String path) throws FileNotFoundException {
        List<String> readLines = new Vector<String>();
        try (Scanner istream = new Scanner(new FileReader(path))){
            while (istream.hasNextLine()) {
                readLines.add(istream.nextLine());
            }
        } catch (FileNotFoundException noFile) {
            throw new FileNotFoundException("At FileUtils.readAll while reading " + path + ": " + noFile.getMessage());
        }
        return readLines;
    }
    /* читаем все строки из файла по пути path и возвращаем этот список */
    public static boolean writeAll(String path, List<String> lines) {
        if (path == null) {
            System.out.println("Path is null");
            return false;
        }
        try(FileWriter ostream = new FileWriter(path, false)) {
            for (String line : lines) {
                ostream.write(line);
                ostream.append(lineSeparator());
            }
            ostream.flush();
        } catch (IOException writingException) {
            System.out.println("Exception happened: " + writingException.getMessage());
            return false;
        }
        return true;

    }
    /*пишем все строки из lines в файла по пути path */
    public static boolean copy(String sourceFile, String destinationFile) throws FileNotFoundException {
        try {
            return writeAll(destinationFile, readAll(sourceFile));
        } catch (FileNotFoundException noFile) {
            throw new FileNotFoundException("At FileUtils.copy while reading " + sourceFile + ": " + noFile.getMessage());
        }
    }
    /*копируем файл по пути sourceFile в destinationFile*/
    public static boolean delete(String path) {
        if (path == null) {
            return false;
        }
        File toDelete = new File(path);
        return (toDelete.delete());
    }
    /*удаляем файл по пути path*/


}
