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
        if (checkingInStorage(r.getUuid())) {
            System.out.println("ОШИБКА: резюме " + r.getUuid() + " находится в хранилище.");
        } else if (storage[storage.length - 1] == null) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("ОШИБКА: хранилище заполнено.");
        }
    }

    public void update(Resume r) {
        if (checkingInStorage(r.getUuid())) {
            int index = searchIndex(r.getUuid());
            storage[index] = r;
        } else {
            System.out.println("ОШИБКА: резюме " + r.getUuid() + " не найдено.");
        }
    }

    public Resume get(String uuid) {
        if (checkingInStorage(uuid)) {
            int index = searchIndex(uuid);
            return storage[index];
        } else {
            System.out.println("ОШИБКА: резюме " + uuid + " не найдено.");
        }
        return null;
    }

    public void delete(String uuid) {
        if (checkingInStorage(uuid)) {
            for (int i = 0; i < size; i++) {
                System.arraycopy(storage, i + 1, storage, i, size - i);
                size--;
                break;
            }
        } else {
            System.out.println("ОШИБКА: резюме " + uuid + " не найдено.");
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

    boolean checkingInStorage(String uuid) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                result = true;
            }
        }
        return result;
    }

    public int searchIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
