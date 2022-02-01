
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null && storage[i + 1] == null) {
                break;
            }
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume foundResume = null;
        for (Resume resume : storage) {
            if (resume != null) {
                if (resume.toString().equals(uuid)) {
                    foundResume = resume;
                    break;
                }
            } else break;
        }
        return foundResume;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
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
        int arrayLength = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                arrayLength++;
            } else {
                break;
            }
        }
        Resume[] currentResume = new Resume[arrayLength];
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                currentResume[i] = storage[i];
            }
        }
        return currentResume;
    }

    int size() {
        int arraySize = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                arraySize++;
            } else {
                break;
            }
        }
        return arraySize;
    }
}
