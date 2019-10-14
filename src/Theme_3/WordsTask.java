package Theme_3;

import java.util.HashSet;
import java.util.Set;

public class WordsTask {
    public static void main(String[] args) {
        String[] arr = {"подход", "офтальмолог", "аббревиатура", "бутафория", "кондуктор",
                        "драма", "издательство","бутафория", "переводчик", "бутафория",
                        "издательство", "подход", "переводчик", "издательство", "издательство",
                        "бутафория", "барокко", "аббревиатура", "подход", "барокко"};
        sortThisArray(arr);
    }

    public static void sortThisArray(String[] array) {
        Set<String> hashSet = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            hashSet.add(array[i]);
        }
        System.out.println(hashSet);
        for (String s : hashSet) {
            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (s == array[i]) count++;
            }
            System.out.println(count + "\t" + s);
        }
    }
}
