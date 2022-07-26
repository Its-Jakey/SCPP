public namespace strings {
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
    public func substring(string, start, end) {
        var ret = "";
        var i = start;

        while (i < end) {
            ret = concat(ret, charAt(string, i));
        }
        return ret;
    }
    public func substring(string, start) {
        return substring(string, start, sizeOf(string));
    }
    public func repeat(string, times) {
        var ret = "";

        for (i from 0 to times)
            ret = concat(ret, string);
        return ret;
    }
    public func indexOfChar(string, char) {
        return _asm_("indexOfChar", string, char);
    }
}
