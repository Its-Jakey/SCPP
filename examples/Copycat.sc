#include <graphics>
#include <input>

namespace copycat {
    public func main() {
        while (1) {
            var input = input::ask("");
            println(input);

            graphics::drawString(input);
            graphics::flip();
        }
    }
}