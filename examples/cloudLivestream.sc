#include <graphics>
#include <strings>
#include <math>
#include <cloud>
#include <time>

#define colorMul 2.58585858586

namespace livestream {
    func drawImage(image) {
        var i = 0;

        for (y from 0 to 64) {
            for (x from 0 to 64) {
                var color = (strings::charAt(image, i)..strings::charAt(image, i + 1)) * colorMul;

                graphics::setColor(graphics::createColor(color, color, color));
                graphics::putPixel(x, y);

                i += 2;
            }
        }
    }

    public func main() {
        while (1) {
            var img = "";

            for (i from 0 to 10)
                img ..= cloud::getVar(i);

            drawImage(cloud::decompress(img, "99", 2, 2));
            graphics::flip();
            graphics::clear();
        }
    }
}