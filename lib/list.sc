#include <arrays>

#define LIST_SIZE 64

public namespace list {
    public var items;
    var index;
    var size;

    public func add(item) {
        items[index] = item;
        index = index + 1;

        if (index > (size - 1)) {
            size += LIST_SIZE;
            items = arrays::resizeArray(items, size - LIST_SIZE, size);
        }
    }

    public func get(idx) {
        return items[idx];
    }

    public func remove(idx) {
        for (i from idx to index) {
            items[i] = items[i - 1];
        }
        index = index - 1;
    }

    public func size() {
        return index;
    }

    public func toArray() {
        var ret = arrays::createSubArray(items, 0, index);
        free(items, size);

        return ret;
    }

    public func getArrayForm() {
        return {index, items};
    }

    public func loadFromArray(array) {
        index = array[0];
        items = array[1];
    }

    public func new() {
        items = malloc(LIST_SIZE);
        index = 0;
        size = LIST_SIZE;
    }

    public func clear() {
        free(items, size);
        items = malloc(LIST_SIZE);
        index = 0;
        size = LIST_SIZE;
    }
}

