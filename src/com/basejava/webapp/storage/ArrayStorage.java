package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = searchIndex(r.getUuid());
        if (index != -1) {
            System.out.println("ОШИБКА: резюме " + r.getUuid() + " находится в хранилище.");
        } else if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("ОШИБКА: хранилище заполнено.");
        }
    }

    public void update(Resume r) {
        int index = searchIndex(r.getUuid());
        if (index == -1) {
            System.out.println("ОШИБКА: резюме " + r.getUuid() + " не найдено.");
        } else {
            storage[index] = r;
        }
    }

    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index == -1) {
            System.out.println("ОШИБКА: резюме " + uuid + " не найдено.");
        } else if (index == storage.length - 1) {
            storage[index] = null;
            size--;
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            size--;
        }
    }


}
