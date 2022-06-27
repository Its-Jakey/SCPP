#include <strings>
#include <math>

namespace numbers {
    var hexChars = "0123456789ABCDEF";

    public func toHex(input) {
        var rem = 0;
        var ret = "";

        while (input > 0) {
            rem = math::floor(input % 16, 0);
            ret = strings::join(strings::charAt(hexChars, rem), ret);
            input = math::floor(input / 16, 0);
        }
        return strings::join("0x", ret);
    }

    public func toBinary(input) {
        var rem = 0;
        var ret = "";

        while (input > 0) {
            rem = math::floor(input % 2, 0);
            ret = strings::join(rem, ret);
            input = math::floor(input / 2, 0);
        }
        return strings::join("0b", ret);
    }

    public func fromHex(input) {
        var ret = 0;
        var tmp = 16;

        for (i from 2 to strings::sizeOf(input)) {
            ret = ret + strings::indexOfChar(hexChars, strings::charAt(input, i));
            ret = ret * 16;
        }
        return ret / 16;
    }

    public func fromBinary(input) {
        var ret = 0;
        var tmp = 2;

        for (i from 2 to strings::sizeOf(input)) {
            ret = ret + strings::charAt(input, i);
            ret = ret * 2;
        }
        return ret / 2;
    }

}
