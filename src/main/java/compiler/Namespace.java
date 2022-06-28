package compiler;

import antlr.SCPPParser;

import java.util.LinkedHashMap;

public class Namespace {
    LinkedHashMap<String, Variable> variables;
    LinkedHashMap<String, Function> functions;
    boolean isPubic;
    String name;
    SCPPParser.NamespaceDeclarationContext context = null;
    String fileName = "";

    public Namespace(String name, boolean isPublic) {
        this.name = name;
        this.isPubic = isPublic;

        variables = new LinkedHashMap<>();
        functions = new LinkedHashMap<>();
    }
}

record Variable(String id, boolean isPublic){}
