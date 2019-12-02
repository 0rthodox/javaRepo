package ru.mipt.hometask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface EntityParser<T> { // Added getId method
    T parse(List<String> lines);
    void write(Writer writer, T entity) throws IOException;
    int getNumOfFields();
    int getId(T entity);
    List<String> read(BufferedReader reader) throws IOException;
}
