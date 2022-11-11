package compiler.memory;

import java.util.LinkedHashMap;

public class Program {
    public LinkedHashMap<String, Namespace> namespaces;
    public Namespace currentNamespace = null;
    public Function currentFunction = null;
    public String fileName;
    public String realFileName;
    public int level = 0;

    public Program(String fileName) {
        this.fileName = fileName;
        this.realFileName = fileName;
        namespaces = new LinkedHashMap<>();
    }

    public Program clone() {
        Program ret = new Program(fileName);
        ret.namespaces = namespaces;
        ret.currentNamespace = currentNamespace;
        ret.currentFunction = currentFunction;
        ret.level = level;

        return ret;
    }
}
