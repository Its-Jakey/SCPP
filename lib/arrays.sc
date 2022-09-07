

public namespace arrays {
    public func createSubArray(array, start, end) {
        var ret = malloc(end - start);

        for (i from start to end)
            ret[i - start] = array[i];
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
        free(array, oldSize);
        return ret;
    }

    public func copyTo(array, newArray, len) {
        for (i from 0 to len)
            newArray[i] = array[i];
    }

    public func join(array, size, seperator) {
        var ret = "";
        for (i from 0 to size) ret ..= array[i]..seperator;
        return ret;
    }

    public func combineArray(array1, size1, array2, size2) {
        var ret = malloc(size1 + size2);

        for (i from 0 to size1)
            ret[i] = array1[i];
        for (i from 0 to size2)
            ret[size1 + i] = array2[i];
        free(array1, size1);
        free(array2, size2);
        
        return ret;
    }
}
