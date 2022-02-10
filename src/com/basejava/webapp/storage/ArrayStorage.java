package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        if (checkingInStorage(r.getUuid())) {
            System.out.println("ОШИБКА: резюме находится в хранилище.");
        } else if (storage[storage.length-1] == null) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("ОШИБКА: хранилище заполнено.");
        }
    }

    public void update(Resume r) {
        if (checkingInStorage(r.getUuid())) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(r.getUuid())) {
                    storage[i] = r;
                    break;
                }
            }
        } else {
            System.out.println("ОШИБКА: резюме не найдено.");
        }
    }

        public Resume get (String uuid){
            if (checkingInStorage(uuid)) {
                for (int i = 0; i < size; i++) {
                    if (storage[i].toString().equals(uuid)) {
                        return storage[i];
                    }
                }
            } else {
                System.out.println("ОШИБКА: резюме не найдено.");
            }
            return null;
        }

        public void delete (String uuid){
            if (checkingInStorage(uuid)) {
                for (int i = 0; i < size; i++) {
                    System.arraycopy(storage, i + 1, storage, i, size - i);
                    size--;
                    break;
                }
            } else {
                System.out.println("ОШИБКА: резюме не найдено.");
            }
        }

        /**
         * @return array, contains only Resumes in storage (without null)
         */
        public Resume[] getAll () {
            Resume[] allResumes = new Resume[size];
            System.arraycopy(storage, 0, allResumes, 0, size);
            return allResumes;
        }

        public int size () {
            return size;
        }

        boolean checkingInStorage (String uuid){
            boolean result = false;
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    result = true;
                }
            }
            return result;
        }
    }
