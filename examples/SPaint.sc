#include <graphics>
#include <strings>
#include <input>
#include <imagelib>

#define BUTTON_SIZE 32
#define COLORS 8
#define OFF_X 400
#define OFF_Y 10
#define IMAGE_SCALE 4

namespace spaint {
    var canvas = imagelib::newImage(64, 64);
    var colorButtons = malloc(COLORS);
    var mode = 0; //Pixel mode
    var currentColor = 0xFFFFFF;
    var lastPixelPressed = malloc(2);
    var pressed = 0;
    var freshClick = 1;

    func initButtons(colors) {
        var x = 0;
        var y = 0;

        for (i from 0 to COLORS) {
            colorButtons[i] = {x + OFF_X, y + OFF_Y, colors[i]};

            x = x + BUTTON_SIZE;
            if (x == (BUTTON_SIZE * 2)) {
                x = 0;
                y = y + BUTTON_SIZE;
            }
        }
    }

    func renderButtons() {
        graphics::setColor(0xFFDA40);
        graphics::fillRect(OFF_X - 2, OFF_Y - 2, (BUTTON_SIZE * 2) + 4, (BUTTON_SIZE * 4) + 4);

        for (i from 0 to COLORS) {
            var color = colorButtons[i];
            graphics::setColor(color[2]);
            graphics::fillRect(color[0], color[1], BUTTON_SIZE, BUTTON_SIZE);
        }

        graphics::setColor(0xFFFFFF);
        graphics::fillRect(300, 10, BUTTON_SIZE, BUTTON_SIZE);

        graphics::setColor(0x000000);
        if (mode)
            graphics::drawLine(302, 12, 298 + BUTTON_SIZE, 8 + BUTTON_SIZE);
        else
            graphics::fillRect(296 + (BUTTON_SIZE / 2), 6 + (BUTTON_SIZE / 2), 8, 8);
    }

    func checkForButtonPress(x, y) {
        for (i from 0 to COLORS) {
            var color = colorButtons[i];
                
            if ((x > color[0]) && (x < (color[0] + BUTTON_SIZE)) && (y > color[1]) && (y < (color[1] + BUTTON_SIZE))) {
                currentColor = color[0];
                return;
            }
        }

        if ((x > 300) && (x < (300 + BUTTON_SIZE)) && (y > 10) && (y < (10 + BUTTON_SIZE)))
            mode = mode < 1;
    }

    func checkForPixelPress(x, y) {
        if (((x - IMAGE_SCALE) < 256) && (x > 0) && ((y - IMAGE_SCALE) < 256) && (y > 0))
             return 1;
        return 0;
    }

    public func main() {
        initButtons({0x000000, 0xFF0000, 0xFFFF00, 0x00FF00, 0x00FFFF, 0x0000FF, 0xFF00FF, 0xFFFFFF});

        while (1) {
            graphics::setColor(0xFFDA40);
            graphics::fillRect(0, 0, 480, 360);
            imagelib::renderImage(canvas, 0, 0, IMAGE_SCALE, IMAGE_SCALE);
            var isPressed = checkForPixelPress(input::mouseX(), input::mouseY());

            if (input::isMouseDown()) {
                if (freshClick) {
                    freshClick = 0;

                    if (isPressed) {
                        if (mode == 1) {
                            if (pressed) {
                                imagelib::drawLineOnImage(canvas, lastPixelPressed[0], lastPixelPressed[1], input::mouseX() / IMAGE_SCALE, input::mouseY() / IMAGE_SCALE, currentColor);
                            } else {
                                lastPixelPressed[0] = input::mouseX() / IMAGE_SCALE;
                                lastPixelPressed[1] = input::mouseY() / IMAGE_SCALE;
                            }
                            pressed = pressed < 1;
                        }
                    } else
                        checkForButtonPress(input::mouseX(), input::mouseY());
                }
                if (mode == 0 && isPressed)
                    canvas[2][input::mouseX() / IMAGE_SCALE][input::mouseY() / IMAGE_SCALE] = currentColor;
            } else
                freshClick = 1;
            graphics::setColor(currentColor);

            if (isPressed && (mode == 1) && (pressed == 1))
                graphics::drawLine(lastPixelPressed[0] * IMAGE_SCALE, lastPixelPressed[1] * IMAGE_SCALE, input::mouseX(), input::mouseY());

            graphics::fillRect(input::mouseX() - IMAGE_SCALE, input::mouseY() - IMAGE_SCALE, IMAGE_SCALE, IMAGE_SCALE);
            renderButtons();

            graphics::flip();
            graphics::clear();
        }
    }
}