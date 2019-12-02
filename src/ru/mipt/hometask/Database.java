package ru.mipt.hometask;

import ru.mipt.hometask.entities.Person;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class Database<T> {
    protected final Path path;
    protected final EntityParser<T> parser;

    public Database(Path databasepath, EntityParser<T> parser) {
        path = databasepath;
        this.parser = parser;
        assertDBExist(path);
    }

    private void assertDBExist(Path path) { //moved from File to Path/Files logic
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new IllegalArgumentException("Unable to create database by given path");
            }
        }
    }

    protected List<T> readAll(Path path) {
        //todo здесь надо придумать логику того, содержится ли объект, который вы хотите получить в кэше(EntityCache)
        // и если содержится, то вытащить его из кэша, а не из файла
        /*File file = path.toFile();
        LinkedList<T> entities = new LinkedList<>();
        FileReader reader;
        BufferedReader bufferedReader;
        try {
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot read file", e);
        }
        while (bufferedReader.read() != -1) {
            String[] lines = new String[parser.getNumOfFields()];
            for (int i = 0; i < lines.length; i++) {
                lines[i] = bufferedReader.readLine();
            }
            entities.add(parser.read(lines));
        }
        return entities;*/
        List<T> entities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
           while (reader.ready()) {
               List<String> lines = new ArrayList<>(parser.getNumOfFields());
               for(int i = 0; i < parser.getNumOfFields(); ++i) {
                   lines.add(reader.readLine());
               }
               entities.add(parser.read(lines));
           }
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
        return entities;
    }

    protected void writeData(List<T> entities) {
        /*File file = path.toFile();
        FileWriter writer;
        BufferedWriter bufferedWriter;
        try {
            writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot read file", e);
        }
        try {
            for (Person person : persons) {
                //todo здесь запись должна осуществляться с помощью parser.write(Writer writer)
                bufferedWriter.write(person.getId());
                bufferedWriter.write(person.getName());
                bufferedWriter.write(person.getSurname());
            }
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Something went wrong");
        }*/
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            for (T entity : entities) {
                parser.write(writer, entity);
            }
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }

    protected Person getEntityById(int id, Path path) throws IOException {
        File file = path.toFile();
        FileReader reader;
        BufferedReader bufferedReader;
        try {
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot read file", e);
        }
        while (bufferedReader.read() != -1) {
            Integer currId = Integer.parseInt(bufferedReader.readLine());
            if (currId == id) {
                return new Person(id, bufferedReader.readLine(), bufferedReader.readLine());
            }
        }
        return new Person(id, "empty", "empty");
    }

    protected void deleteEntityById(int id, Path path) {
        //todo реализовать этот метод
    }

    protected void clearDatabase() {
        //todo реализовать этот метод
    }
}
