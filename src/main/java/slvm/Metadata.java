package slvm;

import java.util.Objects;

public final class Metadata {
    private final String fileName;
    private final String line;

    public Metadata(String fileName, String line) {
        this.fileName = fileName;
        this.line = line;
    }

    public String fileName() {
        return fileName;
    }

    public String line() {
        return line;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Metadata) obj;
        return Objects.equals(this.fileName, that.fileName) &&
                Objects.equals(this.line, that.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, line);
    }

    @Override
    public String toString() {
        return fileName + ":" + line;
    }
}
