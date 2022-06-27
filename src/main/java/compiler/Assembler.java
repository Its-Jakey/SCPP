package compiler;

import java.util.HashMap;
import java.util.Map;

public class Assembler {
    public static String assemble(String assembly) {
        String[] lines;
        StringBuilder ret = new StringBuilder();
        HashMap<String, Integer> labels = new HashMap<>();
        int line_n = 1;

        if (assembly.contains("\n"))
            lines = assembly.split("\n");
        else
            lines = new String[]{assembly};

        for (String line : lines) {
            if (line.startsWith(":"))
                labels.put(line.substring(1), line_n);
            else if (!line.startsWith(";")){
                line_n++;
                ret.append("\n").append(line);
            }
        }
        String tmp;

        if (ret.toString().contains("\n"))
            tmp = ret.substring(1);
        else
            tmp = ret.toString();

        for (Map.Entry<String, Integer> entry : labels.entrySet())
            tmp = tmp.replaceAll("%" + entry.getKey() + "%", entry.getValue() + "");
        return tmp;
    }
}
