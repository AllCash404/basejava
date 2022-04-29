package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    protected Resume[] sortStorage = sortingStorage(storage);

    private Resume[] sortingStorage(Resume[] storage) {
        Resume[] sStorage = Arrays.copyOf(storage, storage.length);
        Resume searchKey;
        for (int i = 0; i < size; i++) {
            searchKey = storage[i];
            int index = Arrays.binarySearch(storage, 0, size, searchKey);
            if (index >= 0) {

            }
            else if (index < -1) {

            }


        }
        return sStorage;
    }

    @Override
    public void clear() {
        Arrays.fill(sortStorage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume r) {
        int index = searchIndex(r.getUuid());
        if (index == -1) {
            System.out.println("ОШИБКА: резюме " + r.getUuid() + " не найдено.");
        } else {
            storage[index] = r;
            sortingStorage(sortStorage);
        }
    }

    @Override
    public void save(Resume r) {
        int index = searchIndex(r.getUuid());
        if (index != -1) {
            System.out.println("ОШИБКА: резюме " + r.getUuid() + " находится в хранилище.");
        } else if (size < storage.length) {
            sortStorage[size] = r;
            size++;
        } else {
            System.out.println("ОШИБКА: хранилище заполнено.");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index == -1) {
            System.out.println("ОШИБКА: резюме " + uuid + " не найдено.");
        } else if (index == sortStorage.length - 1) {
            sortStorage[index] = null;
            size--;
        } else {
            System.arraycopy(sortStorage, index + 1, sortStorage, index, size - index);
            size--;
        }
    }

    @Override
    public int searchIndex(String uuid){
        for (int i = 0; i < size; i++) {
            if (sortStorage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index == -1) {
            System.out.println("ОШИБКА: резюме " + uuid + " не найдено.");
            return null;
        }
        return sortStorage[index];
    }

    @Override
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[size];
        System.arraycopy(sortStorage, 0, allResumes, 0, size);
        return allResumes;
    }
}
