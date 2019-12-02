package ru.mipt.hometask.parsers;

import ru.mipt.hometask.entities.Document;

import java.util.ArrayList;
import java.util.List;

public class DocumentParser extends AbstractParser<Document> {
    private static final int numOfFields = 4;
    @Override
    public Document parse(List<String> lines) {
        return new Document(Integer.parseInt(lines.get(0)), lines.get(1), lines.get(2), Long.parseLong(lines.get(3)));
    }

    @Override
    final List<String> stringify(Document document) {
        List<String> stringified = new ArrayList<>(numOfFields);
        stringified.add(Integer.toString(document.getId()));
        stringified.add(document.getDocumentName());
        stringified.add(document.getDocumentOwner());
        stringified.add(Long.toString(document.getDocumentId()));
        return stringified;
    }

    @Override
    public int getNumOfFields() {
        return numOfFields;
    }

    @Override
    public int getId(Document document) {
        return document.getId();
    }
}
