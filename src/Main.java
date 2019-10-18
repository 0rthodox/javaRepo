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
        n.add(3);
        CustomList m = new NikiLinkedList();
        /*m.add(1);
        m.add(2);
        m.add(3);
        m.add(3);*/
        System.out.println(n.containsSublist(m));
    }
}
