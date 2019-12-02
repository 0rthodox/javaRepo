package ru.mipt.hometask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Database<T> { // changed some access parameters, removed 'path' argument in methods
    private final Path path;     //since this string exists
    private final EntityParser<T> parser;
    private final EntityCache<T> cache = new EntityCache<>();

    public Database(Path databasepath, EntityParser<T> parser) {
        path = databasepath;
        this.parser = parser;
        assertDBExist();
    }

    private void assertDBExist() { //moved from File to Path/Files logic
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new IllegalArgumentException("Unable to create database by given path");
            }
        }
    }

    public List<T> readAll() { // moved to TWR
        List<T> entities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
           while (reader.ready()) {
               List<String> unparsedEntity = parser.read(reader);
               entities.add(parser.parse(unparsedEntity));
           }
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
        return entities;
    }

    public void writeData(List<T> entities) { // Added null assertion, moved to TWR
        if (entities == null) {
            throw new IllegalArgumentException("Given list is null");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            for (T entity : entities) {
                parser.write(writer, entity);
            }
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }

    public T getEntityById(int id) {// moved to TWR, added NSEE if there is no entity with such id
        if (cache.contains(id)) {
            return cache.getFromCache(id);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            while (reader.ready()) {
                List<String> unparsedEntity = parser.read(reader);
                if (id == Integer.parseInt(unparsedEntity.get(0))) {
                    T parsedEntity = parser.parse(unparsedEntity);
                    cache.putInCache(parser.getId(parsedEntity), parsedEntity);
                    return parsedEntity;
                }
            }
            throw new NoSuchElementException("Haven't found entity with id " + id);
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }

    public void deleteEntityById(int id) {//moved to TWR
        List<T> entities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            while (reader.ready()) {
                List<String> unparsedEntity = parser.read(reader);
                if (!Objects.equals(id, Integer.parseInt(unparsedEntity.get(0)))) {
                    entities.add(parser.parse(unparsedEntity));
                }
            }
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
        clearDatabase();
        writeData(entities);
    }

    public void clearDatabase() {
        try {
            Files.delete(path);
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
        cache.clear();
        assertDBExist();
    }
}
