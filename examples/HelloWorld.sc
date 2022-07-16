#include <graphics>

namespace helloWorld {
    public func main() {
        graphics::drawStringLine("Hello World!");
        graphics::flip(); //Render the graphics buffer to the screen
    }
}
