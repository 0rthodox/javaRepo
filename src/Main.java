import ru.mipt.collections.CustomList;
import ru.mipt.collections.NikiLinkedList;
import ru.mipt.collections.NikiList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Should work for now..");
        CustomList n = new NikiLinkedList();
        n.add(1);
        n.add(2);
        n.add(3);
        System.out.println((n.get(0)));
        System.out.println((n.get(1)));
        System.out.println((n.get(2)));
        System.out.println((n.get(3)));
        System.out.println((n.get(4)));
    }
}
