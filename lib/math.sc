public namespace math {
    public func round(x, places) {
        return _asm_("round", x, places);
    }
    public func floor(x, places) {
        return _asm_("floor", x, places);
    }
    public func ceil(x, places) {
        return _asm_("ceil", x, places);
    }
    public func round(x) {
        return _asm_("round", x, 0);
    }
    public func floor(x) {
        return _asm_("floor", x, 0);
    }
    public func ceil(x) {
        return _asm_("ceil", x, 0);
    }
    public func sin(x) {
        return _asm_("sin", x);
    }
    public func cos(x) {
        return _asm_("cos", x);
    }
    public func sqrt(x) {
        return _asm_("sqrt", x);
    }
    public func atan2(x, y) {
        return _asm_("atan2", x, y);
    }
    public func negate(x) {
        if (x > 0)
            return 0 - x;
        return x;
    }
    public func min(a, b) {
        if (a < b) {
            return a;
        }
        return b;
    }
    public func max(a, b) {
        if (a > b) {
            return a;
        }
        return b;
    }
    public func inRange(x, a, b) {
        return x > a && x < b;
    }
    public func abs(x) {
        if (x < 0)
            return (0 - x);
        return x;
    }
    public func pow(x, y) {
        var ret = 0;

        for (i from 0 to y)
            ret = ret + x;
        return ret;
    }
}
