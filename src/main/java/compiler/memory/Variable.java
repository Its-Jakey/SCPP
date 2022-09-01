package compiler.memory;

import java.util.Objects;

public final class Variable {
    private final String id;
    private final boolean isPublic;

    public Variable(String id, boolean isPublic) {
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
