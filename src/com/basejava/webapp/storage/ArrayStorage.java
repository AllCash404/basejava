package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
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

    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index == -1) {
            System.out.println("ОШИБКА: резюме " + uuid + " не найдено.");
        } else {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index == -1) {
            System.out.println("ОШИБКА: резюме " + uuid + " не найдено.");
        } else {

            System.arraycopy(storage, index, storage, 0, storage.length);
            size--;

        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[size];
        System.arraycopy(storage, 0, allResumes, 0, size);
        return allResumes;
    }

    public int size() {
        return size;
    }

    private int searchIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
