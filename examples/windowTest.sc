#include <window>
#include <graphics>

namespace window1 is window;
namespace window2 is window;

namespace windowTest {
    public func main() {
        window1::setPosition(100, 150);
        window1::setSize(200, 150);

        while (1) {
            graphics::clear();

            window1::tick();
            window2::tick();

            graphics::flip();
        }
    }
}
