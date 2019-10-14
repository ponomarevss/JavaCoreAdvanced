package Theme_3;

import java.util.HashMap;
import java.util.Map;

public class ContactList {

    public static HashMap<Contact, String> contactList = new HashMap<>();

    public static void main(String[] args) {
        add("Ivanov", "45-67");
        add("Petrov", "42-74");
        add("Sidorov", "45-60");
        add("Sidorov", "44-81");
        add("Sidorov", "44-87");
        add("Ivanov", "45-61");
        add("Ivanov", "45-63");

        get("Ivanov");
        get("Sidorov");

    }

    public static void add(String name, String number) {
        Contact contact = new Contact(name, number);
        contactList.put(contact, contact.getName() + "\t" + contact.getNumber());
    }

    public static void get(String name) {
        for (Map.Entry<Contact, String> o : contactList.entrySet()) {
            if (o.getKey().getName() == name) {
                System.out.println(o.getValue());
            }
        }
    }
}
