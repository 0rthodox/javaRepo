package ru.mipt.iterators;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;

public class FileIterator implements Iterator<String>, AutoCloseable {

    private BufferedReader reader;
    private FileReader fileReader;

    public FileIterator(String path) {
        try {
            fileReader = new FileReader(path);
            reader = new BufferedReader(fileReader);
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
            return reader.readLine();
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileIterator iterator = (FileIterator) o;
        return Objects.equals(reader, iterator.reader) &&
                Objects.equals(fileReader, iterator.fileReader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reader, fileReader);
    }

    @Override
    public void close() {
        try {
            if (reader != null) {
                reader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }
}
