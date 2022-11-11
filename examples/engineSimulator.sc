#include <graphics>
#include <time>
#include <input>
#include <math>
#include <sound>

#define crankshaftSize 75
#define journalSize 25
#define crankshaftX 220
#define crankshaftY 160
#define rodLength 100
#define pistonWidth 50
#define pistonHeight 25
#define rodOffset 0
#define wristPinDiameter 10
#define PI 3.14159265359

namespace engineSimulator {
    var RPM = 600;
    var rotationPerCycle = 0;
    var currentRotation = 0;

    var rotationRingSize = crankshaftSize * 0.5;
    var rotationRingX = crankshaftX + (crankshaftSize / 2) - (rotationRingSize / 2);
    var rotationRingY = crankshaftY + (crankshaftSize / 2) - (rotationRingSize / 2);

    var journalX = rotationRingX + (rotationRingSize / 2) - (journalSize / 2);
    var journalY = rotationRingY - (journalSize / 2);
    var rotSteps = rotationRingSize / 2;

    var pistonX = crankshaftX;
    var pistonY = crankshaftY - rodLength;

    var millisPerCycle = 1000 / 60;
    var lastTime = 0;

    var freshFire = 0;
    var freshCheck = 0;
    var rotations = 0;

    func cycle() {
        var start = time::getRuntimeMillis();

        journalX += (rotSteps * rotationPerCycle) * math::cos(currentRotation);
        journalY += (rotSteps * rotationPerCycle) * math::sin(currentRotation);

        var journelCenterY = journalY + (journalSize / 2);
        var journelCenterX = journalX + (journalSize / 2);

        var rodAngle = math::atan2(journelCenterY - (pistonY + rodOffset), journelCenterX - (pistonX + (pistonWidth / 2)));
        var rodStartX = journelCenterX;
        var rodStartY = journelCenterY;
        var rodEndX = pistonX + (pistonWidth / 2);
        var rodEndY = journelCenterY + (rodLength * math::sin(rodAngle)) + rodOffset;

        //println(rodEndY, pistonY, rodAngle);
        pistonY = rodEndY;

        graphics::setColor(0xCDCDCD);
        graphics::fillCircle(crankshaftX, crankshaftY, crankshaftSize);
        
        graphics::setColor(0x838383);
        graphics::fillCircle(journalX, journalY, journalSize);

        graphics::setColor(0xFF0000);
        graphics::drawLine(rodStartX, rodStartY, rodEndX, rodEndY);

        graphics::setColor(0xD8D8D8);
        graphics::fillRect(pistonX, pistonY, pistonWidth, pistonHeight);

        graphics::setColor(0x000000);
        graphics::fillCircle(pistonX + (pistonWidth / 2) - (wristPinDiameter / 2), pistonY + rodOffset + (wristPinDiameter / 2), wristPinDiameter);

        graphics::goto(0, 10);
        graphics::setColor(0xFFFFFF);
        graphics::drawStringLine("RPM: "..RPM);
        graphics::drawStringLine("Rotations: "..rotations);


        graphics::flip();
        graphics::clear();

        if ((math::floor(rotations, 0) % 2) == 0) {
            if (freshFire) {
                sound::startSound("pop");
                freshFire = 0;
            }
        } else
            freshFire = 1;
        
        if ((math::round(rotations, 0) % math::round((RPM / 60), 0)) == 0) {
            if (freshCheck) {
                if (input::isKeyPressed(input::KEY_UP))
                    RPM += 25;
                else if (input::isKeyPressed(input::KEY_DOWN))
                    RPM -= 25;
                freshCheck = 0;
            }
        } else
            freshCheck = 1;

        currentRotation += rotationPerCycle;

        rotations = currentRotation / (2 * PI);

        
        lastTime = time::getRuntimeMillis() - start;
        time::sleep(millisPerCycle - lastTime);

    }

    func setTimes() {
        //millisPerCycle = lastTime * 1.1;

        var rotationsPerMilliSecond = RPM / 60000;
        var cyclesPerRotation = rotationsPerMilliSecond * millisPerCycle;
        rotationPerCycle = cyclesPerRotation * (PI * 2);
    }

    public func main() {
        //rotationPerCycle = 360 * (((RPM / 60) / 1000) * millisPerCycle);
        rotationPerCycle = PI / 180;

        while (1) {
            cycle();
            setTimes();
        }
    }
}