package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public SortedArrayStorage() {
        sortingStorage();
    }

    private void sortingStorage() {
        Resume searchKey = new Resume();
        for (int i = 0; i < size; i++) {
            searchKey.setUuid(storage[i].getUuid());
            int index = Arrays.binarySearch(storage, 0, size, searchKey);
            if (index >= 0) {
                storage[index] = storage[i];
            } else if (index < -1) {
                Resume buf = storage[i];
                System.arraycopy(storage, i + 1, storage, i, size);
                storage[index * -1 - 2] = buf;
            }
        }
    }

    @Override
    public void save(Resume r) {
        int index = searchIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("ОШИБКА: резюме " + r.getUuid() + " находится в хранилище.");
        } else if (size < storage.length) {
            if (size != 0) {
                System.arraycopy(storage, index * -1 - 1, storage, index * -1, size);
                storage[index * -1 - 1] = r;
            } else
                storage[size] = r;
            size++;
        } else {
            System.out.println("ОШИБКА: хранилище заполнено.");
        }
    }

    @Override
    protected int searchIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size(), searchKey);
    }
}
