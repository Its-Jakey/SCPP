#include <list>


namespace stackList is list;


public namespace valueStack {
    public func clear() {
        stackList::clear();
    }

    public func push(item) {
        stackList::add(item);
    }

    public func pop() {
        var ret = stackList::get(stackList::size() - 1);
        stackList::remove(stackList::size() - 1);
        return ret;
    }

    public func peek() {
        return stackList::get(stackList::size() - 1);
    }

    public func get(index) {
        return stackList::get(index);
    }

    public func size() {
        return stackList::size();
    }
}

namespace stackList2 is list;


public namespace subStack {
    public func clear() {
        stackList2::clear();
    }

    public func push(item) {
        stackList2::add(item);
    }

    public func pop() {
        var ret = stackList2::get(stackList2::size() - 1);
        stackList2::remove(stackList2::size() - 1);
        return ret;
    }

    public func peek() {
        return stackList2::get(stackList2::size() - 1);
    }

    public func get(index) {
        return stackList2::get(index);
    }

    public func size() {
        return stackList2::size();
    }
}
