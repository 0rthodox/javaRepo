import ru.mipt.collections.CustomList;
import ru.mipt.collections.FileUtils;
import ru.mipt.collections.NikiLinkedList;
import ru.mipt.collections.NikiList;

import java.io.File;
import java.util.List;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        List<String> l = new Vector<String>();
        l.add("Once");
        l.add("Upon");
        l.add("A");
        l.add("Time");
        FileUtils.writeAll("output.txt", l);
        List<String> newL = new Vector<String>(FileUtils.readAll("output.txt"));
        FileUtils.writeAll("output2.txt", newL);
        FileUtils.copy("output.txt", "output3.txt");
        FileUtils.delete("output.txt");
        System.out.println("Should work for now..");
    }
}
