#include <strings>
#include <math>


public namespace numbers {
    var CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public func fromBase10(dec, radix) {
        var ret = "";
        while (dec > 0) {
            ret ..= strings::charAt(CHARS, dec % radix);
            dec = math::floor(dec / radix, 0);
        }
        return ret;
    }

    public func toBase10(num, radix) {
        var ret = 0;
        for (i from 0 to strings::sizeOf(num))
            ret += strings::firstIndexOf(CHARS, strings::charAt(num, i)) * math::pow(radix, i);
        return ret;
    }
}
