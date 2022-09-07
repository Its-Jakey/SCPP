#include <arrays>

public namespace strings {
    public var splitSize;

    public func charAt(string, idx) {
        return _asm_("charAt", string, idx);
    }
    public func sizeOf(string) {
        return _asm_("sizeOf", string);
    }
    public func contains(string, x) {
        return _asm_("contains", string, x);
    }
    public func join(a, b) {
        _asm_("loadAtVar", a);
        return _asm_("join", b);
    }
    public func toAscii(x) {
        return _asm_("toAsciiValue", x);
    }
    public func fromAscii(x) {
        return _asm_("fromAsciiValue", x);
    }
    public func substring(string, start, end) {
        var ret = "";
        for (i from start to end)
            ret = ret .. charAt(string, i);
        return ret;
    }
    public func substring(string, start) {
        return substring(string, start, sizeOf(string));
    }
    public func repeat(string, times) {
        var ret = "";

        for (i from 0 to times)
            ret ..= string;
        return ret;
    }
    public func indexOfChar(string, char) {
        return _asm_("indexOfChar", string, char);
    }

    public func endsWith(string, end) {
        var stringSize = sizeOf(string);
        var endSize = sizeOf(end);
        var start = stringSize - endSize;

        if (start < 0)
            return 0;

        for (i from 0 to endSize)
            if (charAt(string, start + i) != charAt(end, i))
                return 0;
        return 1;
    }

    public func startsWith(string, start) {
        var stringSize = sizeOf(string);
        var startSize = sizeOf(start);

        if (stringSize < startSize)
            return 0;

        for (i from 0 to startSize)
            if (charAt(string, i) != charAt(start, i))
                return 0;
        return 1;
    }

    public func firstIndexOf(string, x) {
        var size = sizeOf(string);
        for (i from 0 to size)
            if (charAt(string, i) == x)
                return i;
        return -1;
    }

    public func lastIndexOf(string, x) {
        var size = sizeOf(string);
        var i = size - 1;

        while (i > 0) {
            if (charAt(string, i) == x)
                return i;
            i--;
        }
        return -1;
    }

    public func split(text, at) { //at must be a char
        var ret = malloc(0);
        var cur = "";
        splitSize = 0;

        for (i from 0 to sizeOf(text)) {
            if (charAt(text, i) == at) {

                splitSize = splitSize + 1;
                ret = arrays::resizeArray(ret, splitSize - 1, splitSize);
                ret[splitSize - 1] = cur;

                cur = "";
            } else {
                cur ..= charAt(text, i);
            }
        }
        if (cur != "") {
            splitSize = splitSize + 1;
            ret = arrays::resizeArray(ret, splitSize - 1, splitSize);
            ret[splitSize - 1] = cur;
        }
        return ret;
    }
}
