#include <strings>

public namespace stringList {
    var items = "";
    var size = 0;

    public func clear() {
        items = "";
        size = 0;
    }

    public func add(item) {
        items ..= item..";";
        size++;
    }

    public func getOutput() {
        return items;
    }

    public func size() {
        return size;
    }

    public func toArray() {
        return strings::split(items, ";");
    }
} 
