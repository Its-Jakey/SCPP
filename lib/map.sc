#define MAX_SIZE 128

public namespace map {
    var keys = malloc(MAX_SIZE);
    var values = malloc(MAX_SIZE);
    var ptr = 0;

    public var EMPTY_KEY = "EMPTY_KEY_RANDOM_LETTERSJFJFKJLDSKJLDSFKLJSDKLJdfsdfsddsjJ8234758JKJH";

    public func put(key, value) {
        keys[ptr] = key;
        values[ptr] = value;
    }

    public func get(key) {
        for (idx from 0 to ptr) {
            if (keys[idx] == key)
                return values[idx];
        }
     
       return -1;
    }

    public func getAt(idx) {
        return values[idx];
    }
    
    public func getKeyAt(idx) {
        return keys[idx];
    }

    public func remove(key) {
        for (idx from 0 to ptr) {
            if (keys[idx] == key) {
                keys[idx] = EMPTY_KEY;
                return;
            }
        }
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
