#include <arrays>
#include <strings>

public namespace state {
    public var currentDir = "/";

    public func getAbsolutePath(path) {
        if (path == ".")
            return currentDir;
        if (path == "..")
            return "/"..arrays::join(arrays::createSubArray(strings::split(path, "/"), 0, strings::splitSize - 1), strings::splitSize - 1, "/");
        if (strings::charAt(path, 0) == "/")
            return path;
        if (strings::charAt(path, strings::sizeOf(path) - 1) == "/")
            return currentDir..path;
        return currentDir..path.."/";
    }
}