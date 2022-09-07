package compiler.optimizer;

import java.util.HashMap;
import java.util.Map;

public class Assembler {
    public static String assemble(String assembly) {
        String[] lines;
        StringBuilder ret = new StringBuilder();
        HashMap<String, Integer> labels = new HashMap<>();
        int line_n = 0;

        if (assembly.contains("\n"))
            lines = assembly.split("\n");
        else
            lines = new String[]{assembly};

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            if (line.startsWith(":") && !lines[i - 1].equals("ldi"))
                labels.put(line.substring(1), line_n);
            else if (!line.startsWith("---#")) {
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
