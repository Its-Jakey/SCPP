

public namespace input {
    public var KEY_UP = "up arrow";
    public var KEY_DOWN = "down arrow";
    public var KEY_LEFT = "left arrow";
    public var KEY_RIGHT = "right arrow";
    public var KEY_SPACE = "space";
    public var KEY_ENTER = "enter";
    public var KEY_ESCAPE = "escape";

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
