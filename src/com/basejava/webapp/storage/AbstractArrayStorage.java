package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected Resume[] storage = new Resume[10000];
    protected int size = 0;

    @Override
    public abstract void clear();

    @Override
    public abstract void update(Resume r);

    @Override
    public abstract void save(Resume r);

    @Override
    public abstract void delete(String uuid);

    @Override
    public abstract Resume get(String uuid);


    @Override
    public abstract Resume[] getAll();

    @Override
    public int size() {
        return size;
    }

    protected int searchIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
