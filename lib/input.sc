

namespace input {
    public func mouseX() {
        return _asm_("mouseX");
    }
    public func mouseY() {
        return _asm_("mouseY");
    }
    public func isMouseDown() {
        return _asm_("mouseDown");
    }
    public func isKeyPressed(key) {
        return _asm_("isKeyPressed", key);
    }
    public func ask(message) {
        return _asm_("ask", message);
    }
}
