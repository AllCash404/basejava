package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{
   protected Resume[] sortStorage = sortingStorage(storage);

   private Resume[] sortingStorage (Resume[] storage){
    Resume[] sStorage = Arrays.copyOf(storage, storage.length);
    Resume searchKey = new Resume();
    for (int i = 1; i <= size; i++) {
     searchKey.setUuid("uuid" + Integer.toString(i));
     sStorage[i-1] = storage[Arrays.binarySearch(storage, 0, size, searchKey)];
    }
    return sStorage;
   }

   @Override
    public void clear() {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }
}
