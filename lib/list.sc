#include <arrays>

#define MAX_LIST_SIZE 128

public namespace list {
    public var items = malloc(MAX_LIST_SIZE);
    var index = 0;

    public func add(item) {
        items[index] = item;
        index = index + 1;
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
        return arrays::createSubArray(items, 0, index);
    }

    public func getArrayForm() {
        return {index, items};
    }

    public func loadFromArray(array) {
        index = array[0];
        items = array[1];
    }
}

