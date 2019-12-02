package ru.mipt.hometask.parsers;

import ru.mipt.hometask.entities.Book;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class BookParser extends AbstractParser<Book> {
    private static final int numOfFields = 4;
    @Override
    List<String> stringify(Book book) {
        List<String> stringified = new ArrayList<>(numOfFields);
        stringified.add(Integer.toString(book.getId()));
        stringified.add(book.getAuthor());
        stringified.add(book.getTitle());
        stringified.add(book.getPublishingYear().toInstant().toString());
        return stringified;
    }

    @Override
    public int getNumOfFields() {
        return numOfFields;
    }

    @Override
    public int getId(Book book) {
        return book.getId();
    }

    @Override
    public Book parse(List<String> lines) {
        return new Book(Integer.parseInt(lines.get(0)), lines.get(1), lines.get(2), Date.from(Instant.parse(lines.get(3))));
    }
}
