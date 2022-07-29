package compiler;

import antlr.SCPPParser;

import java.util.LinkedHashMap;
import java.util.Objects;

public class Namespace {
    LinkedHashMap<String, Variable> variables;
    LinkedHashMap<String, Function> functions;
    boolean isPubic;
    String name;
    SCPPParser.NamespaceDeclarationContext context = null;
    String fileName = "";
    int level = 0;

    public Namespace(String name, boolean isPublic) {
        this.name = name;
        this.isPubic = isPublic;

        variables = new LinkedHashMap<>();
        functions = new LinkedHashMap<>();
    }
}

final class Variable {
    private final String id;
    private final boolean isPublic;

    Variable(String id, boolean isPublic) {
        this.id = id;
        this.isPublic = isPublic;
    }

    Variable(Variable old, boolean isPublic) {
        this.id = old.id;
        this.isPublic = isPublic;
    }

    public String id() {
        return id;
    }

    public boolean isPublic() {
        return isPublic;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Variable) obj;
        return Objects.equals(this.id, that.id) &&
                this.isPublic == that.isPublic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isPublic);
    }

    @Override
    public String toString() {
        return "Variable[" +
                "id=" + id + ", " +
                "isPublic=" + isPublic + ']';
    }
}
