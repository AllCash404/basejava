package com.basejava.webapp;

import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.ArrayStorage;
import com.basejava.webapp.storage.SortedArrayStorage;

/**
 * Test for your com.basejava.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");


        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        r1.setUuid("uuid4");
        ARRAY_STORAGE.update(r1);

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.get(r2.getUuid()));


    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
