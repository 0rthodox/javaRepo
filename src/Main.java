
import ru.mipt.iterators.FileIterator;


public class Main {
    public static void main(String[] args) {
        FileIterator iterator = new FileIterator("output3.txt");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        iterator.close();
    }
}
