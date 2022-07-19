package slvm;

import java.util.*;
import java.util.stream.Collectors;

public class VMException extends RuntimeException {

    public static String getDebugInfo() {
        List<Metadata> reversed = new ArrayList<>(SLVM.metadata);
        Collections.reverse(reversed);

        return reversed.stream().collect(StringBuilder::new, (str, metadata) -> str.append("\n\tat ").append(metadata), StringBuilder::append).toString();
    }
    public VMException(String msg) {
        super(msg + "\n\tat " + SLVM.fileName + ":" + SLVM.line + getDebugInfo());
    }
    public VMException(String msg, String fileName) {
        super(msg + "\n\tat " + fileName + ":" + SLVM.pc + "\n\tat " + SLVM.fileName + ":" + SLVM.line + getDebugInfo());
    }
}
