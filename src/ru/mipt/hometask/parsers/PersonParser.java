package ru.mipt.hometask.parsers;

import ru.mipt.hometask.EntityParser;
import ru.mipt.hometask.entities.Person;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PersonParser extends AbstractParser<Person> {
    private static final int numOfFields = 3;
    @Override
    public Person read(List<String> lines) {
        return new Person(Integer.parseInt(lines.get(0)), lines.get(1), lines.get(2));
    }

    @Override
    List<String> stringify(Person person) {
        List<String> stringified = new ArrayList<>(numOfFields);
        stringified.add(Integer.toString(person.getId()));
        stringified.add(person.getName());
        stringified.add(person.getSurname());
        return stringified;
    }
}
