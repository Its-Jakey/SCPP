#include <graphics> 
#include <math>


public namespace imagelib {
    public func deleteImage(image) {
        for (x from 0 to image[0])
            free(image[2], image[1]);
        free(image, 3);
    }

    public func newImage(width, height) {
        var ret = malloc(width);

        for (i from 0 to width)
            ret[i] = malloc(height);
        return {width, height, ret};
    }

    public func fillImage(image, color) {
        for (x from 0 to image[0])
            for (y from 0 to image[1])
                image[2][x][y] = color;
    }

    public func setPixelOnImage(image, x, y, color) {
        if ((x < 0) || (x > (image[0] - 1)) || (y < 0) || (y > (image[1] - 1)))
            return;
        image[2][x][y] = color;
    }

    public func getPixelFromImage(image, x, y) {
        if ((x < 0) || (x > (image[0] - 1)) || (y < 0) || (y > (image[1] - 1)))
            return 0;
        return image[2][x][y];
    }

    public func drawLineOnImage(image, x0, y0, x1, y1, color) {
        x0 = math::floor(x0);
        y0 = math::floor(y0);
        x1 = math::floor(x1);
        y1 = math::floor(y1);
        
        //DDA line algorithm
        var dx = x1 - x0;
        var dy = y1 - y0;
        var steps = math::max(math::abs(dx), math::abs(dy));
        var xinc = dx / steps;
        var yinc = dy / steps;
        var x = x0;
        var y = y0;
        for (i from 0 to steps) {
            setPixelOnImage(image, x, y, color);
            x += xinc;
            y += yinc;
        }
    }

    public func fillRectOnImage(image, x, y, width, height, color) {
        for (i from 0 to width)
            for (j from 0 to height)
                setPixelOnImage(image, x + i, y + j, color);
    }

    public func drawImageOnImage(image, x, y, otherImage) {
        for (i from 0 to otherImage[0])
            for (j from 0 to otherImage[1])
                setPixelOnImage(image, x + i, y + j, getPixelFromImage(otherImage, i, j));
    }

    public func renderImage(image, x, y) {
        for (i from 0 to image[0])
            for (j from 0 to image[1]) {
                graphics::setColor(image[2][i][j]);
                graphics::putPixel(i + x, j + y);
            }
    }

    public func renderImage(image, x, y, width, height) {
        for (i from 0 to image[0])
            for (j from 0 to image[1]) {
                graphics::setColor(image[2][i][j]);
                graphics::fillRect((i * width) + x, (j * height) + y, width, height);
            }
    }

    public func scaleImage(image, scale) {
        var newImage = newImage(image[0] * scale, image[1] * scale);

        for (i from 0 to image[0])
            for (j from 0 to image[1])
                fillRectOnImage(newImage, i * scale, j * scale, scale, scale, getPixelFromImage(image, i, j));
        return newImage;
    }
}