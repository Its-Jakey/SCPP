#include <graphics>
#include <input>
#include <math>
#include <time>

#define MAP_WIDTH 64
#define MAP_HEIGHT 64
#define STEP 2
#define PLAYER_STEP 0.5
#define PLAYER_ROTATE 0.19634954084
#define FOV 0.78539816339
#define PI 3.14159265359
#define ANGLE_SKIP 1

namespace raycaster {
    var map = malloc(MAP_WIDTH);
    var VIEW_DISTANCE = MAP_WIDTH + (MAP_HEIGHT / 2);
    var player;
    var caster = malloc(3);

    func moveCaster(steps) {
        caster[0] = caster[0] + (steps * math::cos(caster[2]));
        caster[1] = caster[1] + (steps * math::sin(caster[2]));
    }

    func checkCollision() {
        if ((caster[0] > MAP_WIDTH) || (caster[0] < 0) || (caster[1] > MAP_HEIGHT) || (caster[1] < 0))
            return 1;

        return map[caster[0]][caster[1]];
    }

    func castRay() {
        var distance = 0;

        while (checkCollision() == 0) {
            moveCaster(STEP);
            distance = distance + STEP;
        }
        
        while (checkCollision()) {
            moveCaster(-1);
            distance--;
        }
        //println(caster[0], caster[1], caster[2]);
        
        //distance = distance * math::cos(caster[2] - player[2]);
        return distance;
    }

    func draw(distance, x, width) {
        //println(distance);
        
        if (distance < (VIEW_DISTANCE - 1)) {
            var scale = (1 - (distance / VIEW_DISTANCE));

            graphics::setColor((255 * scale) << 8);
            var height = 360 * scale;
            graphics::fillRect(x, (360 - height) / 2, width, (360 - height));
            //graphics::drawLine(x, (360 - height) / 2, x, 360 - ((360 - height) / 2));
        }
    }

    func castRays() {
        caster[2] = player[2] - (FOV / 2);
        var fovDegrees = FOV * (180 / PI);

        for (angle from 0 to 480 by ANGLE_SKIP) {
            caster[0] = player[0];
            caster[1] = player[1];
            draw(castRay(), angle, ANGLE_SKIP);
            caster[2] = (FOV / 480) * angle;
        }
        graphics::flip();
        graphics::clear();
    }

    func drawLineOnMap(x0, y0, x1, y1) {
        var dx = math::abs(x1 - x0);
        var dy = math::abs(y1 - y0);
        var err = dx - dy;

        var sx = -1;
        if (x0 < x1)
            sx = 1;

        var sy = -1;
        if (y0 < y1)
            sy = 1;

        while (1) {
            map[x0][y0] = 1;
            if ((x0 == x1) && (y0 == y1))
                return;

            var e2 = 2 * err;
            if (e2 > (0 - dy)) {
                err = err - dy;
                x0 = x0 + sx;
            }
            if (e2 < dx) {
                err = err + dx;
                y0 = y0 + sy;
            }
        }
    }

    func drawHorizontalLineOnMap(x0, y0, x1) {
        var sx = -1;
        if (x0 < x1)
            sx = 1;

        while (1) {
            map[x0][y0] = 1;
            if (x0 == x1)
                return;

            x0 = x0 + sx;
        }
    }

    func drawVirticleLineOnMap(x0, y0, y1) {
        var sy = -1;
        if (y0 < y1)
            sy = 1;

        while (1) {
            map[x0][y0] = 1;
            if (y0 == y1)
                return;

            y0 = y0 + sy;
        }
    }

    func drawMap() {
        graphics::setColor(0xFFFFFF);
        for (x from 0 to MAP_WIDTH)
            for (y from 0 to MAP_HEIGHT)
                if (map[x][y])
                    graphics::putPixel(x, y);
    }

    func printMap() {
        for (x from 0 to MAP_WIDTH) {
            for (y from 0 to MAP_HEIGHT)
                print(map[x][y], "");
            println();
        }
    }

    func movePlayer() {
        if (input::isKeyPressed(input::KEY_UP))
            player[1] = player[1] - PLAYER_STEP;
        if (input::isKeyPressed(input::KEY_DOWN))
            player[1] = player[1] + PLAYER_STEP;
        if (input::isKeyPressed(input::KEY_LEFT))
            player[2] = player[2] - PLAYER_ROTATE;
        if (input::isKeyPressed(input::KEY_RIGHT))
            player[2] = player[2] + PLAYER_ROTATE;
    }

    public func main() {
        for (i from 0 to MAP_WIDTH)
            map[i] = malloc(MAP_HEIGHT);
        player = {MAP_WIDTH / 2, MAP_HEIGHT / 2, 0};

        /*
        drawHorizontalLineOnMap(0, 0, MAP_WIDTH);
        drawHorizontalLineOnMap(0, 1, MAP_WIDTH);
        drawHorizontalLineOnMap(0, MAP_HEIGHT - 1, MAP_WIDTH);
        drawHorizontalLineOnMap(0, MAP_HEIGHT - 2, MAP_WIDTH);
        drawVirticleLineOnMap(0, 0, MAP_HEIGHT);
        drawVirticleLineOnMap(1, 0, MAP_HEIGHT);
        drawVirticleLineOnMap(MAP_WIDTH - 1, 0, MAP_HEIGHT);
        drawVirticleLineOnMap(MAP_WIDTH - 2, 0, MAP_HEIGHT);
        */
        //printMap();

        while (1) {
            var startTime = time::getRuntimeMillis();
            castRays();
            movePlayer();
            var endTime = time::getRuntimeMillis();

            time::sleep(16.6666666667 - (endTime - startTime));
        }
    }
}