package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int searchIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size(), searchKey);
    }

    @Override
    protected void saveInStorage(Resume resume, int index) {
        int positiveIndex = index * -1;
        if (size != 0) {
            if (size == storage.length - 1) {
                System.arraycopy(storage, positiveIndex - 1, storage, positiveIndex, size - 1 );
            } else {
                System.arraycopy(storage, positiveIndex - 1, storage, positiveIndex, size);
            }
            storage[positiveIndex - 1] = resume;
        } else
            storage[size] = resume;
    }
}
