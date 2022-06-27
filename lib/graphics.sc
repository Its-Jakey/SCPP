

namespace graphics {
    public func putPixel(x, y) {
        _asm_("putPixel", x, y);
    }
    public func drawLine(x0, y0, x1, y1) {
        _asm_("putLine", x0, y0, x1, y1);
    }
    public func fillRect(x, y, width, height) {
        _asm_("putRect", x, y, width, height);
    }
    public func setColor(color) {
        _asm_("setColor", color);
    }
    public func setStrokeWidth(strokeWidth) {
        _asm_("setStrokeWidth", strokeWidth);
    }
    public func clear() {
        _asm_("clg");
    }
    public func drawString(string) {
        _asm_("drawText", string);
    }
    public func drawStringLine(line) {
        _asm_("drawText", line);
        _asm_("newLine");
    }
    public func createColor(r, g, b) {
        return _asm_("createColor", r, g, b);
    }
    public func flip() {
        _asm_("graphicsFlip");
    }
    public func newLine() {
        _asm_("newLine");
    }
    public func goto(x, y) {
        _asm_("goto", x, y);
    }
}
