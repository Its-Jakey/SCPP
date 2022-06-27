#include <graphics>

namespace triangle {
    public func main() {
        graphics::setColor(graphics::createColor(0, 0, 255)); //Blue

        graphics::drawLine(100, 100, 150, 200); //Left side
        graphics::drawLine(100, 100, 200, 100); //Bottom
        graphics::drawLine(150, 200, 200, 100); //Right side

        graphics::flip();
    }
}
