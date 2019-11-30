package ru.mipt.iterators;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class FileIterator implements Iterator<String>, AutoCloseable {

    private BufferedReader reader;

    public FileIterator(String path) {
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException noFile) {
            throw new IllegalArgumentException("Unable to initialize FileIterator : the path " + path + " is invalid");
        }
    }

    @Override
    public boolean hasNext() {
        try {
            return reader.ready();
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }

    @Override
    public String next() {
        try {
            String nextString;
            nextString = reader.readLine();
            if (nextString == null) {
                throw new NoSuchElementException("Can't find the next string");
            }
            return nextString;
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }

    @Override
    public void close() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }
}
