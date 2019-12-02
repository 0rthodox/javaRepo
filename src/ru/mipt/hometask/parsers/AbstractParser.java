package ru.mipt.hometask.parsers;

import ru.mipt.hometask.EntityParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParser<T> implements EntityParser<T> {

    abstract List<String> stringify(T entity);

    @Override
    public void write(Writer writer, T entity) throws IOException {
        List<String> stringified = stringify(entity);
        for (int i = 0; i < stringified.size(); ++i) {
            writer.write(stringified.get(i) + System.lineSeparator());
        }
    }

    @Override
    public List<String> read(BufferedReader reader) throws IOException {
        List<String> stringedEntity = new ArrayList<>(getNumOfFields());
        for (int i = 0; i < getNumOfFields(); ++i) {
            stringedEntity.add(reader.readLine());
        }
        return stringedEntity;
    }

    @Override
    public abstract int getNumOfFields();

    public abstract int getId(T entity);
}
