import ru.mipt.collections.NikiList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Should work for now..");
        NikiList testList = new NikiList(3);
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(4);
        testList.add(4);
        NikiList sublist = new NikiList(0);
        sublist.add(3);
        sublist.add(4);
        System.out.println(testList.containsSublist(sublist));
        System.out.println(sublist.containsAll(sublist));
        System.out.println(sublist.containsAll(testList));

        System.out.println(testList.get(0));
    }
}
