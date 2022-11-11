#include <strings>
#include <arrays>


public namespace file {
    public var name;
    public var isDirectory;
    public var children = malloc(1);
    public var childrenCount;
    public var data;
    public var fileSize = 5;

    public func addChild(file) {
        children[childrenCount] = file;

        childrenCount = childrenCount + 1;
        children = arrays::resizeArray(children, childrenCount, childrenCount + 1);
    }

    public func getChild(name) {
        for (i from 0 to childrenCount) 
            if (children[i][0] == name)
                return children[i];
        return -1;
    }

    public func new() {
        name = "";
        isDirectory = 0;
        children = malloc(1);
        childrenCount = 0;
        data = "";
    }

    public func pack() {
        return {name, isDirectory, children, childrenCount, data};
    }

    public func removeChild(index) {
        free(children[index], fileSize);

        for (i from index to childrenCount - 1)
            children[i] = children[i + 1];
        childrenCount--;
    }

    public func unpack(file) {
        name = file[0];
        isDirectory = file[1];
        children = file[2];
        childrenCount = file[3];
        data = file[4];
    }

    public func createRootFile() {
        new();
        isDirectory = 1;
        return pack();
    }
}

public namespace fs {
    public var root = file::createRootFile();

    public func getFile(path) {
        var files = strings::split(path, "/");
        var cur = root;

        for (i from 1 to strings::splitSize) {
            file::unpack(cur);
            cur = file::getChild(files[i]);

            if (cur == -1)
                return -1;
        }
        return cur;
    }

    public func readFromFile(path) {
        return getFile(path)[4];
    }

    public func exists(path) {
        var files = strings::split(path, "/");
        var cur = root;

        for (i from 1 to strings::splitSize) {
            file::unpack(cur);
            cur = file::getChild(files[i]);

            if (cur == -1)
                return 0;
        }
        return 1;
    }

    public func createFile(path, name) {
        file::new();
        file::name = name;
        file::isDirectory = 0;
        var newFile = file::pack();

        if (exists(path) == 0)
            println("Unknown directory:", path);
        else {
            var tmp = getFile(path);

            file::unpack(tmp);
            file::addChild(newFile);
            arrays::copyTo(file::pack(), tmp, file::fileSize);

            return tmp;
        }
    }

    public func removeFile(path) {
        var files = strings::split(path, "/");
        var parentPath = arrays::join(arrays::createSubArray(files, 0, strings::splitSize - 1), strings::splitSize - 1, "/");
        var name = files[strings::splitSize - 1];

        var parent = getFile(parentPath);
        var fileIndex = -1;

        file::unpack(parent);
        for (i from 0 to file::childrenCount) {
            var child = file::children[i];

            if (child[0] == name) {
                fileIndex = i;
                i = 99999;
            }
        }
        if (fileIndex != -1) {
            file::removeChild(fileIndex);
            arrays::copyTo(file::pack(), parent, file::fileSize);
        }
    }

    public func createFile(path_) {
        var files = strings::split(path_, "/");
        var path = arrays::join(arrays::createSubArray(files, 0, strings::splitSize - 1), strings::splitSize - 1, "/");
        var name = files[strings::splitSize - 1];

        createFile(path, name);
    }

    public func getParentDirectory(path) {
        var files = strings::split(path, "/");
        return arrays::join(arrays::createSubArray(files, 0, strings::splitSize - 1), strings::splitSize - 1, "/");
    }

    public func isDirectory(path) {
        var files = strings::split(path, "/");
        var cur = root;

        for (i from 1 to strings::splitSize) {
            file::unpack(cur);
            cur = file::getChild(files[i]);

            if (cur == -1)
                return 0;
        }
        return cur[1];
    }

    public func createDirectory(path_) {
        var files = strings::split(path_, "/");
        var path = arrays::join(arrays::createSubArray(files, 0, strings::splitSize - 1), strings::splitSize - 1, "/");
        var name = files[strings::splitSize - 1];

        file::new();
        file::name = name;
        file::isDirectory = 1;
        var newFile = file::pack();

        if (exists(path) == 0)
            println("Unknown directory:", path);
        else {
            var tmp = getFile(path);

            file::unpack(tmp);
            file::addChild(newFile);
            arrays::copyTo(file::pack(), tmp, file::fileSize);
        }
    }

    public func writeToFile(path, data) {
        var files = strings::split(path, "/");
        var parentPath = arrays::join(arrays::createSubArray(files, 0, strings::splitSize - 1), strings::splitSize - 1, "/");

        if (exists(path) == 0)
            createFile(parentPath, files[strings::splitSize - 1]);
        var tmp = getFile(path);
        tmp[4] = data;
    }

    public func listDirectory(dir) {
        file::unpack(getFile(dir));
        var ret = "Directory of "..dir;
        var dirs = 0;

        for (i from 0 to file::childrenCount) {
            var child = file::children[i];

            if (child[1]) { //If file is a directory
                ret ..= "\n<DIR>    "..child[0];
                dirs = dirs + 1;
            } else
                ret ..= "\n         "..child[0];
        }
        ret ..= "\n\t"..(file::childrenCount - dirs).." File(s), "..dirs.." Dir(s)";
        return ret;
    }
}
