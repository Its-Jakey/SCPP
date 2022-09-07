#include <strings>
#include <arrays>

public namespace utils {
    public var splitSize;

    public func splitCommand(command, at) {
        var ret = malloc(0);
        var cur = "";
        splitSize = 0;

        for (i from 0 to strings::sizeOf(command)) {
            var char = strings::charAt(command, i);

            if (char == "'") {
                i++;
                char = strings::charAt(command, i);

                while (char != "'") {
                    cur ..= char;

                    i++;
                    char = strings::charAt(command, i);
                }
            } else if (char == at) {
                splitSize++;

                ret = arrays::resizeArray(ret, splitSize - 1, splitSize);
                ret[splitSize - 1] = cur;

                cur = "";
            } else
                cur ..= char;
        }
        if (cur != "") {
            splitSize++;

            ret = arrays::resizeArray(ret, splitSize - 1, splitSize);
            ret[splitSize - 1] = cur;
        }
        return ret;
    }
}