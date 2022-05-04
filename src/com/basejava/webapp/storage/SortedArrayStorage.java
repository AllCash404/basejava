package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    protected Resume[] sortStorage = Arrays.copyOf(storage, storage.length);

    private void sortingStorage() {
        Resume searchKey = new Resume();
        for (int i = 0; i < size; i++) {
            searchKey.setUuid(sortStorage[i].getUuid());
            int index = Arrays.binarySearch(sortStorage, 0, size, searchKey);
            if (index >= 0) {
                sortStorage[index] = sortStorage[i];
            }
            else if (index < -1) {
               Resume buf = sortStorage[i];
               System.arraycopy(sortStorage, i + 1, sortStorage, i, size);
               sortStorage[index * -1 -2] = buf;
            }
        }
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
            storage[index * -1 - 1] = r;
            System.out.println("Резюме обновлено");
            sortingStorage();
        }
    }

    @Override
    public void save(Resume r) {
        int index = searchIndex(r.getUuid());
        if (index >= 0) {
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

    @Override
    protected int searchIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(sortStorage, 0, size(), searchKey);
    }
}