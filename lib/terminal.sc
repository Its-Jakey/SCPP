#include <graphics>
#include <input>
#include <strings>
#include <time>

#define ROWS 25
#define COLS 40
#define CURSOR_CYCLE_TIME 750

public namespace terminal {
    var rows = malloc(ROWS);
    var rowIdx = 0;
    var cursorX = 0;
    var cursorY = 0;
    var lastTime = 0;
    var lastKeyPressed = "";
    var showCursor = 0;
    var cursorVisable = 0;

    public func scroll(lines) {
        for (i from lines to ROWS)
            rows[lines - i] = i;
    }
    public func render() {
        graphics::clear();

        for (i from 0 to ROWS)
            graphics::drawStringLine(rows[i]);
        if (showCursor)
            graphics::fillRect((cursorX * 8) - 1, (cursorY * 8) + 1, 6, 8);
        graphics::flip();
    }
    func scrollIfNeeded() {
        if (rowIdx == ROWS) {
            scroll(1);
            rowIdx = rowIdx - 1;
        }
    }
    public func drawLine(text) {
        scrollIfNeeded();
        var screenIdx = 0;

        for (i from 0 to strings::sizeOf(text)) {
            if (screenIdx == 40) {
                screenIdx = screenIdx % 40;
                rowIdx = rowIdx + 1;
                scrollIfNeeded();
            }
            rows[rowIdx] = concat(rows[rowIdx], strings::charAt(text, i));
            screenIdx = screenIdx + 1;
        }

        rowIdx = rowIdx + 1;
        render();
    }
    public func addLine(text) {
        rowIdx = rowIdx - 1;
        var screenIdx = 0;

        for (i from 0 to strings::sizeOf(text)) {
            if (screenIdx == 40) {
                screenIdx = screenIdx % 40;
                rowIdx = rowIdx + 1;
                scrollIfNeeded();
            }
            rows[rowIdx] = concat(rows[rowIdx], strings::charAt(text, i));
            screenIdx = screenIdx + 1;
        }

        rowIdx = rowIdx - 1;
        render();
    }
    public func clear() {
        for (i from 0 to ROWS)
            rows[i] = "";
    }
    public func cycleCursor() {
        if (time::getRuntimeMillis() > (lastTime + CURSOR_CYCLE_TIME)) {
            showCursor = showCursor < 1;
            lastTime = time::getRuntimeMillis();
        }
    }
    public func fixCursorBounds() {
        if (cursorX > COLS) {
            cursorY = cursorY + 1;
            cursorX = cursorX - COLS;
        }
        if (cursorX < 0)
            cursorX = cursorX + COLS;
        if (cursorY > ROWS)
            cursorY = ROWS;
        if (cursorY < 0)
            cursorY = 0;
    }
    public func moveCursor() {
        if (input::isKeyPressed("right arrow")) {
            cursorX = cursorX + 1;
            fixCursorBounds();
            showCursor = 1;
            return;
        }
        if (input::isKeyPressed("left arrow")) {
            cursorX = cursorX - 1;
            fixCursorBounds();
            showCursor = 1;
            return;
        }
        if (input::isKeyPressed("up arrow")) {
            cursorY = cursorY - 1;
            fixCursorBounds();
            showCursor = 1;
            return;
        }
        if (input::isKeyPressed("down arrow")) {
            cursorY = cursorY + 1;
            fixCursorBounds();
            showCursor = 1;
            return;
        }
    }
    public func init() {
        clear();
        lastTime = time::getRuntimeMillis();
    }
    public func tick() {
        if (cursorVisable) {
            moveCursor();
            cycleCursor();
        }
        render();
    }
    public func setCursorVisable(visable) {
        cursorVisable = visable;
    }
}
