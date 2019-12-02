package ru.mipt.hometask;

import java.io.Writer;
import java.util.List;

public interface EntityParser<T> {
    T read(List<String> lines);
    void write(Writer writer, T entity);
    int getNumOfFields();
}
