package compiler;

import java.util.LinkedHashMap;

public class Program {
    LinkedHashMap<String, Namespace> namespaces;
    Namespace currentNamespace = null;
    Function currentFunction = null;
    String fileName;
    boolean isTopLevel = false;

    public Program(String fileName) {
        this.fileName = fileName;
        namespaces = new LinkedHashMap<>();
    }

    public Program clone() {
        Program ret = new Program(fileName);
        ret.namespaces = namespaces;
        ret.currentNamespace = currentNamespace;
        ret.currentFunction = currentFunction;
        ret.isTopLevel = isTopLevel;

        return ret;
    }
}
