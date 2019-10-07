import ru.mipt.collections.NikiList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Second attempt");
        NikiList testList = new NikiList(3);
        testList.add(1);
        testList.add(1);
        testList.add(1);
        testList.add(1);
        System.out.println(testList.get(3));
    }
}
