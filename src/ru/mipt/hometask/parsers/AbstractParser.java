package ru.mipt.hometask.parsers;

import ru.mipt.hometask.EntityParser;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.List;

public abstract class AbstractParser<T> implements EntityParser<T> {
    private static int numOfFields;

    abstract List<String> stringify(T entity);

    @Override
    public void write(Writer writer, T entity) {
        try {
            List<String> stringified = stringify(entity);
            for (int i = 0; i < stringified.size(); ++i) {
                writer.write(stringified.get(i) + System.lineSeparator());
            }
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }

    }

    @Override
    public int getNumOfFields() {
        return numOfFields;
    }
}
