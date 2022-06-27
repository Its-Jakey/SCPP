#include <graphics>
#include <input>

namespace copycat {
    public func main() {
        while (1) {
            graphics::drawString(input::ask(""));
            graphics::flip();
        }
    }
}