#define MAX_SIZE 128

public namespace map {
    var keys = malloc(MAX_SIZE);
    var values = malloc(MAX_SIZE);
    var ptr = 0;

    public func put(key, value) {
        keys[ptr] = key;
        values[ptr] = value;
    }

    public func get(key) {
        for (idx from 0 to ptr) {
            if (keys[idx] == key) {
                return values[idx];
            }
        }
        return -1;
    }

    public func size() {
        return ptr;
    }

    public func getArrayForm() {
        return {ptr, keys, values};
    }

    public func loadFromArray(array) {
        ptr = array[0];
        keys = array[1];
        values = array[2];
    }
}
