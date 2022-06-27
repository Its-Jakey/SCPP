#include <graphics>
#include <time>

namespace count {
    public func main() {
        for (i from 0 to 999999) {
            graphics::drawString(i);
            graphics::flip();
            graphics::clear();
        }
    }
}
