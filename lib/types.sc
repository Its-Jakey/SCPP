#include <errors>

namespace types {
    public func errorIfNotNumber(input) {
        var type = typeOf(input);

        if (type == "int" || type == "float")
            return;

    }
}