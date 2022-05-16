package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected Resume[] storage = new Resume[10000];
    protected int size = 0;

    @Override
    public void update(Resume r) {
        int index = searchIndex(r.getUuid());
        if (index < 0) {
            System.out.println("ОШИБКА: резюме " + r.getUuid() + " не найдено.");
        } else {
            storage[index] = r;
            System.out.println("Резюме обновлено");
        }
    }

    @Override
    public void save(Resume r) {
        int index = searchIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("ОШИБКА: резюме " + r.getUuid() + " находится в хранилище.");
        } else if (size < storage.length) {
            saveInStorage(r, index);
            size++;
        } else {
            System.out.println("ОШИБКА: хранилище заполнено.");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = searchIndex(uuid);
        if (index < 0) {
            System.out.println("ОШИБКА: резюме " + uuid + " не найдено.");
        } else if (index == size - 1) {
            storage[index] = null;
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        }
        if (index >= 0)
            size--;
    }

    @Override
    public Resume get(String uuid) {
        int index = searchIndex(uuid);
        if (index < 0) {
            System.out.println("ОШИБКА: резюме " + uuid + " не найдено.");
            return null;
        }
        return storage[index];
    }

    @Override
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[size];
        System.arraycopy(storage, 0, allResumes, 0, size);
        return allResumes;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int searchIndex(String uuid);

    protected abstract void saveInStorage(Resume resume, int index);
}
