

public namespace arrays {
    public func createSubArray(array, start, end) {
        var ret = malloc(end - start);

        for (i from start to end)
            ret[i] = array[i];
        return ret;
    }

    public func createArrayCopy(array, size) {
        var ret = malloc(size);

        for (i from 0 to size)
            ret[i] = array[i];
        return ret;
    }

    public func resizeArray(array, oldSize, newSize) {
        var ret = malloc(newSize);

        for (i from 0 to oldSize)
            ret[i] = array[i];
        return ret;
    }

    public func copyTo(array, newArray, len) {
        for (i from 0 to len)
            newArray[i] = array[i];
    }
}
