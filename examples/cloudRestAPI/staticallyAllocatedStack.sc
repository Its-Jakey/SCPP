

#define ALLOCATION 64

public namespace staticallyAllocatedStack {
    var values;
    var ptr;

    public func push(value) {
        values[ptr] = value;
        ptr++;
    }

    public func pop() {
        ptr--;
        return values[ptr];
    }

    public func peek() {
        return values[ptr - 1];
    }

    public func init() {
        values = malloc(ALLOCATION);
        ptr = 0;
    }

    public func clear() {
        free(values, ALLOCATION);
        init();
    }

    public func size() {
        return ptr;
    }
}