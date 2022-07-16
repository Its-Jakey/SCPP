//Program ported from Processing, in turorial https://www.instructables.com/3D-Wireframe-Engine-Using-Processing/

#include <graphics>
#include <list>
#include <math>
#include <input>
#include <time>

#define PI 3.14159265359
#define MSPF 16.6666666667
#define BACKGROUND_COLOR 0x000000

namespace wireframe {
    var screenWidth = 480;
    var screenHeight = 360;
    var heightDiv = screenHeight / 2;
    var widthDiv = screenWidth / 2;
    var camPosition = {0, 0, 0}; //X, z, Y (I don't know why Y is last)

    var direction = PI / 8;
    var rotationY = 0;
    var fov = PI / 2;
    var speed = 0.1;

    var piMul = 2 * PI; //Faster than calcuating each frame
    var negPiMul = 0 - piMul;

    var wires = malloc(2048); // Wire array storage: wire{pos{x, z, y}, pos{x, z, y}}
    var wireCount = 0;
    var running = 1;

    func relative(posA, x, y, z) {
        return {posA[0] + x, posA[1] + y, posA[2] + z};
    }
    func pointOnCanvas(pos){

        var angleH = math::atan2(pos[1], pos[0]);
        var angleV = math::atan2(pos[2], pos[0]);

        var tmpH = math::abs(math::cos(angleH));
        var tmpV = math::abs(math::cos(angleV));

        angleH = angleH / tmpH;
        angleV = angleV / tmpV;

        return {widthDiv - ((angleH * screenWidth) / fov), heightDiv - ((angleV * screenHeight) / fov)};
    }
    func toCamCoords(pos){
        var rPos = {pos[0] - camPosition[0], pos[1] - camPosition[1], pos[2] - camPosition[2]};
        var negDir = 0 - direction;
        var negRot = 0 - rotationY;
        //calculating rotation
        var rx = rPos[0];
        var ry = rPos[1];
        var rz = rPos[2];

        //rotation z-axis
        rPos[0] = rx * math::cos(negDir) - ry * math::sin(negDir);
        rPos[1] = rx * math::sin(negDir) + ry * math::cos(negDir);

        //rotation y-axis
        rx = rPos[0];
        rz = rPos[2];
        rPos[0] = rx * math::cos(negRot) + rz * math::sin(negRot);
        rPos[2] = rz * math::cos(negRot) - rx * math::sin(negRot);

        return rPos;
    }

    func moveCamera() {
        var tmp;
        if (input::isKeyPressed("left arrow")) {
            direction = direction + speed;

            if (direction > piMul)
                direction = direction - piMul;
        }
        if (input::isKeyPressed("right arrow")) {
            direction = direction - speed;

            if (direction < 0)
                direction = direction + piMul;
        }
        if (input::isKeyPressed("down arrow")) {
            rotationY = rotationY + speed;

            if (rotationY > piMul)
                rotationY = rotationY - piMul;
        }
        if (input::isKeyPressed("up arrow")) {
            rotationY = rotationY - speed;

            if (rotationY < 0)
                rotationY = rotationY + piMul;
        }
        if (input::isKeyPressed("w")) {
            camPosition[0] = camPosition[0] + math::cos(direction);
            camPosition[1] = camPosition[1] + math::sin(direction);
        }
        if (input::isKeyPressed("s")) {
            camPosition[0] = camPosition[0] - math::cos(direction);
            camPosition[1] = camPosition[1] - math::sin(direction);
        }
        if (input::isKeyPressed("a")) {
            tmp = direction + 1.5708;
            camPosition[0] = camPosition[0] + math::cos(tmp);
            camPosition[1] = camPosition[1] + math::sin(tmp);
        }
        if (input::isKeyPressed("d")) {
            tmp = direction + 1.5708;
            camPosition[0] = camPosition[0] - math::cos(tmp);
            camPosition[1] = camPosition[1] - math::sin(tmp);
        }

        if (input::isKeyPressed("space"))
            camPosition[2] = camPosition[2] + 1;

        if (input::isKeyPressed("shift"))
            camPosition[2] = camPosition[2] - 1;
    }

    func draw() {
        graphics::clear();
        graphics::setColor(BACKGROUND_COLOR);
        graphics::fillRect(0, 0, 480, 360);

        graphics::setColor(0xFFFFFF);
        graphics::drawLine(widthDiv - 5, heightDiv, widthDiv + 5, heightDiv);
        graphics::drawLine(widthDiv, heightDiv - 5, widthDiv, heightDiv + 5);

        for (i from 0 to wireCount) {
            var wire = wires[i];
            graphics::setColor(wire[2]);
            //println(wire);
            var camStart = toCamCoords(wire[0]);
            var camEnd = toCamCoords(wire[1]);
            var drawStart = pointOnCanvas(camStart);
            var drawEnd = pointOnCanvas(camEnd);

            //println(wire[0][0], wire[0][1], wire[0][2], wire[1][0], wire[1][1], wire[1][2]);
            //println(drawStart[0] + widthDiv, drawStart[1] + heightDiv, drawEnd[0] + widthDiv, drawEnd[1] + heightDiv);

            graphics::drawLine(drawStart[0], drawStart[1], drawEnd[0], drawEnd[1]);

            free(camStart, 3);
            free(camEnd, 3);
            free(drawStart, 2);
            free(drawEnd, 2);
        
        }
    }

    public func createCube(pos, size, color) {
        wires[wireCount] = {relative(pos, size, size, size), relative(pos, 0, size, size), color};
        wires[wireCount + 1] = {relative(pos, size, 0, size), relative(pos, 0, 0, size), color};
        wires[wireCount + 2] = {relative(pos, size, size, size), relative(pos, size, 0, size), color};
        wires[wireCount + 3] = {relative(pos, 0, size, size), relative(pos, 0, 0, size), color};
        wires[wireCount + 4] = {relative(pos, size, size, 0), relative(pos, 0, size, 0), color};
        wires[wireCount + 5] = {relative(pos, size, 0, 0), relative(pos, 0, 0, 0), color};
        wires[wireCount + 6] = {relative(pos, size, size, 0), relative(pos, size, 0, 0), color};
        wires[wireCount + 7] = {relative(pos, 0, size, 0), relative(pos, 0, 0, 0), color};
        wires[wireCount + 8] = {relative(pos, size, size, size), relative(pos, size, size, 0), color};
        wires[wireCount + 9] = {relative(pos, size, 0, size), relative(pos, size, 0, 0), color};
        wires[wireCount + 10] = {relative(pos, 0, 0, size), relative(pos, 0, 0, 0), color};
        wires[wireCount + 11] = {relative(pos, 0, size, size), relative(pos, 0, size, 0), color};
        wireCount = wireCount + 12;
    }

    public func createPyramid(pos, size, color) {
        var topLeft = relative(pos, 0, 0, 0);
        var topRight = relative(pos, size, 0, 0);
        var bottomLeft = relative(pos, 0, size, 0);
        var bottomRight = relative(pos, size, size, 0);

        //Base
        wires[wireCount] = {topLeft, topRight, color};
        wires[wireCount + 1] = {topLeft, bottomLeft, color};
        wires[wireCount + 2] = {bottomLeft, bottomRight, color};
        wires[wireCount + 3] = {bottomRight, topRight, color};

        var centerPoint = relative(pos, size / 2, size / 2, size);
        //Sides
        wires[wireCount + 4] = {topLeft, centerPoint, color};
        wires[wireCount + 5] = {topRight, centerPoint, color};
        wires[wireCount + 6] = {bottomLeft, centerPoint, color};
        wires[wireCount + 7] = {bottomRight, centerPoint, color};

        wireCount = wireCount + 8;
    }

    public func createHorizontalCircle(pos, size, color, steps) {
        var angleIncrease = 2 * (PI / steps);
        var angle = angleIncrease;

        for (i from 1 to steps + 1) {
            var start = relative(pos, math::sin(angle - angleIncrease) * size, math::cos(angle - angleIncrease) * size, 0);
            var end = relative(pos, math::sin(angle) * size, math::cos(angle) * size, 0);
            angle = angle + angleIncrease;
            

            wires[wireCount] = {start, end, color};
            wireCount = wireCount + 1;
        }
    }

    public func createSphere(pos, size, color, steps) {
        var sizeInc = size / (steps / 2);
        var midWay = steps / 2;
        var currentSize = sizeInc;

        for (y from 1 to steps - 1) {
            var padding = currentSize / 2;
            createHorizontalCircle(relative(pos, currentSize, currentSize, 0), currentSize, color, steps);

            if (y > midWay)
                currentSize = currentSize - sizeInc;
            if (y < midWay || y == midWay)
                currentSize = currentSize + sizeInc;
        }
    }

    public func drawLine(start, end, color) {
        wires[wireCount] = {start, end, color};
        wireCount = wireCount + 1;
    }
    func drawCordinateReference() {
        var zero = {0, 0, 0};

        drawLine(zero, {0, 0, 100}, 0x22FF00);
        drawLine(zero, {100, 0, 0}, 0xFF002B);
        drawLine(zero, {0, 100, 0}, 0x0077FF);
    }
    func setupScene() {
        drawCordinateReference();
        createCube({100, 50, 0}, 50, 0x00FFFF);
        createCube({50, 50, 0}, 25, 0xFF0000);
        createPyramid({110, 110, 0}, 50, 0x0000FF);
        //createHorizontalCircle({200, 200, 0}, 50, 0xFFFF00, 24);
        createSphere({200, 200, 0}, 50, 0xFFFF00, 24);
    }

    public func main() {
        setupScene();
        var FPS = 0;

        while (1) {
            while (running) {
                var start = time::getRuntimeMillis();

                moveCamera();
                draw();
                graphics::setColor(0x00FF00);
                graphics::drawString(concat("FPS: ", math::round(FPS, 2)));
                graphics::flip();

                var end = time::getRuntimeMillis();
                var time = end - start;
                FPS = 1000 / time;

                if (time < MSPF)
                    time::sleep(MSPF - time);
                if (input::isKeyPressed("escape")) {
                    while (input::isKeyPressed("escape")) {}
                    running = 0;
                }
            }
            graphics::setColor(0xFFFFFF);
            graphics::goto(230, 172);
            graphics::drawString("paused");
            graphics::flip();

            running = 1;
            while (input::isKeyPressed("escape") < 1) {}
            while (input::isKeyPressed("escape")) {}
        }
    }
}
