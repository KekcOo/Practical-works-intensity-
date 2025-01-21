import collections.ArrayListEasyVersion;
import collections.HashMapEasyVersion;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ArrayListEasyVersion<String> list = new ArrayListEasyVersion<>();

        list.add("Vasiliy");
        list.add("Ivan");
        list.add("Svetlana");
        System.out.println(list);


        System.out.println("--------------------------");
        System.out.println("Get method : "+list.get(1));

        System.out.println("--------------------------");

        list.remove(1);
        System.out.println("Remove 1 index : "+list);
        System.out.println("--------------------------");

        System.out.println("Find by index existing item :  "+ list.indexOf("Svetlana")
                + " Find by index not existing item : " +list.indexOf("Katy"));
        System.out.println("--------------------------");

        list.clear();
        System.out.println("After clear : "+ list);
        System.out.println("--------------------------");
        System.out.println("--------------------------");

        HashMapEasyVersion<String, Integer> map = new HashMapEasyVersion<>();
        for (int i = 0; i < 400; i++) {
            map.put(i + " number", i);

        }

        System.out.println("Map: " + map);


        System.out.println("Size map: " + map.size());

        map.clear();
        System.out.println("After clear: " + map);

    }
}