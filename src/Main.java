import ru.mipt.hometask.Database;
import ru.mipt.hometask.EntityParser;
import ru.mipt.hometask.entities.Book;
import ru.mipt.hometask.entities.Document;
import ru.mipt.hometask.entities.Person;
import ru.mipt.hometask.parsers.BookParser;
import ru.mipt.hometask.parsers.DocumentParser;
import ru.mipt.hometask.parsers.PersonParser;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        EntityParser<Person> parser = new PersonParser();
        Database<Person> personDatabase = new Database<>(Paths.get("persons.txt"), parser);
        Person Denis = new Person(1, "Denis", "Donerov");
        Person Nikita = new Person(2, "Nikita", "Tsaryov");
        Person Anton = new Person(3, "Anton", "Kouropatkin");
        List<Person> neighbours = new ArrayList<Person>(3);
        neighbours.add(Denis);
        neighbours.add(Nikita);
        neighbours.add(Anton);
        System.out.println("Surnames of people to be put in database:");
        for (int i = 0; i < neighbours.size(); ++i) {
            System.out.println(neighbours.get(i).getSurname());
        }
        personDatabase.writeData(neighbours);
        List<Person> parsedNeighbours = personDatabase.readAll();
        System.out.println("Surnames of parsed people:");
        for (int i = 0; i < parsedNeighbours.size(); ++i) {
            System.out.println(parsedNeighbours.get(i).getSurname());
        }
        personDatabase.deleteEntityById(2);
        parsedNeighbours = personDatabase.readAll();
        System.out.println("Surnames of parsed people after deletion:");
        for (int i = 0; i < parsedNeighbours.size(); ++i) {
            System.out.println(parsedNeighbours.get(i).getSurname());
        }
        Person foundPerson = personDatabase.getEntityById(1);
        System.out.println("Name of parsed person: " + foundPerson.getName());
        foundPerson = personDatabase.getEntityById(1);
        System.out.println("Name of parsed person: " + foundPerson.getName());
        personDatabase.clearDatabase();
        Database<Book> bookDatabase = new Database<>(Paths.get("books.txt"), new BookParser());
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "A.Umnov", "MMM Course", new Date()));
        System.out.println(books.get(0).getId());
        System.out.println(books.get(0).getAuthor());
        System.out.println(books.get(0).getTitle());
        System.out.println(books.get(0).getPublishingYear());
        bookDatabase.writeData(books);
        List<Book> parsedBooks = bookDatabase.readAll();
        System.out.println(parsedBooks.get(0).getId());
        System.out.println(parsedBooks.get(0).getAuthor());
        System.out.println(parsedBooks.get(0).getTitle());
        System.out.println(parsedBooks.get(0).getPublishingYear());
        bookDatabase.deleteEntityById(1);
        bookDatabase.deleteEntityById(1);
        Database<Document> documentDatabase = new Database<>(Paths.get("docs.txt"), new DocumentParser());
        List<Document> documents = new ArrayList<>();
        documents.add(new Document(1, "Contract", "I.", 9223372036854775806L));
        System.out.println(books.get(0).getId());
        System.out.println(books.get(0).getAuthor());
        System.out.println(books.get(0).getTitle());
        System.out.println(books.get(0).getPublishingYear());
        documentDatabase.writeData(documents);
        List<Document> parsedDocs = documentDatabase.readAll();
        System.out.println(parsedDocs.get(0).getId());
        System.out.println(parsedDocs.get(0).getDocumentName());
        System.out.println(parsedDocs.get(0).getDocumentOwner());
        System.out.println(parsedDocs.get(0).getDocumentId());
        documentDatabase.clearDatabase();
    }
}
