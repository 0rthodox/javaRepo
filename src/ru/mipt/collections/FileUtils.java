package ru.mipt.collections;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.System.lineSeparator;

public class FileUtils {
    public static List<String> readAll(String path) throws FileNotFoundException {
        List<String> readLines = new Vector<String>();
        try (Scanner istream = new Scanner(new FileReader(path))){
            while (istream.hasNextLine()) {
                readLines.add(istream.nextLine());
            }
        } catch (FileNotFoundException noFile) {
            throw noFile;
        }
        return readLines;
    }
    /* читаем все строки из файла по пути path и возвращаем этот список */
    public static boolean writeAll(String path, List<String> lines) throws UncheckedIOException {
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
            throw new UncheckedIOException("Something went wrong when writing: " + writingException.getMessage());
        }
        return true;

    }
    /*пишем все строки из lines в файла по пути path */
    public static boolean copy(String sourceFile, String destinationFile) {
        try {
            writeAll(destinationFile, readAll(sourceFile));
        } catch (FileNotFoundException noFile) {
            System.out.println("Exception happened: " + noFile.getMessage());
            return false;
        }
        return true;
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
