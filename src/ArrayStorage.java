
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null && storage[i + 1] == null) {
                break;
            }
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                size++;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null) {
                if (resume.toString().equals(uuid)) {
                    return resume;
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
                size--;
                break;
            }
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = storage[i + 1];
                storage[i + 1] = null;
                if (storage[i] == null && storage[i + 1] == null) {
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes  = new Resume[size];
        System.arraycopy(storage, 0, allResumes, 0, size);
        return allResumes ;
    }

    int size() {
        return size;
    }
}
