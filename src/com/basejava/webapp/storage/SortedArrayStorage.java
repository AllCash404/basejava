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
        if (size != 0) {
            System.arraycopy(storage, index * -1 - 1, storage, index * -1, size);
            storage[index * -1 - 1] = resume;
        } else
            storage[size] = resume;
    }
}
