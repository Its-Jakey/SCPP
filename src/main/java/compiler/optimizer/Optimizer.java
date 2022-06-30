package compiler.optimizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Optimizer {
    /* Cases to optimize
        storeAtVar var loadAtVar var storeAtVar var2 -> storeAtVar var storeAtVar var2 (lines 1 & 3)
        loadAtVar var storeAtVar var2 loadAtVar var -> loadAtVar var storeAtVar var2 (lines 1 & 2)
        loadAtVar var storeAtVar var -> nothing
        storeAtVar var loadAtVar var -> storeAtVar var (line 1)
     */

    public static String optimize(String code) {
        StringBuilder ret = new StringBuilder();
        String[] lines = code.split("\n");

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].startsWith(":")) {
                ret.append("\n").append(lines[i]);
                continue;
            }
            if (i + 5 < lines.length) {
                if (lines[i].equals("storeAtVar") && lines[i + 2].equals("loadAtVar") && lines[i + 4].equals("storeAtVar") && lines[i + 1].equals(lines[i + 3])) {
                    if (lines[i + 3].equals(lines[i + 5])) {
                        ret.append("\n").append(lines[i]).append("\n").append(lines[i + 1]);

                        i += 5;
                        continue;
                    }
                    ret
                            .append("\n")
                            .append(lines[i])
                            .append("\n")
                            .append(lines[i + 1])
                            .append("\n")
                            .append(lines[i + 4])
                            .append("\n")
                            .append(lines[i + 5]);
                    i += 5;
                } else if (lines[i].equals("loadAtVar") && lines[i + 2].equals("storeAtVar") && lines[i + 4].equals("loadAtVar") && lines[i + 1].equals(lines[i + 5])) {
                    ret
                            .append("\n")
                            .append(lines[i])
                            .append("\n")
                            .append(lines[i + 1])
                            .append("\n")
                            .append(lines[i + 2])
                            .append("\n")
                            .append(lines[i + 3]);
                    i += 5;
                } else
                    ret.append("\n").append(lines[i]);
            } else {
                if (i + 3 < lines.length) {
                    if (lines[i].equals("loadAtVar") && lines[i + 2].equals("storeAtVar") && lines[i + 1].equals(lines[i + 3]))
                        i += 3;
                    else if (lines[i].equals("storeAtVar") && lines[i + 2].equals("loadAtVar") && lines[i + 1].equals(lines[i + 3])) {
                        ret.append("\n").append(lines[i]).append("\n").append(lines[i + 1]);
                        i += 3;
                    } else
                        ret.append("\n").append(lines[i]);
                } else
                    ret.append("\n").append(lines[i]);
            }

        }
        //System.out.println("Optimizer reduced assembly from " + lines.length + " lines down to " + ret.substring(1).split("\n").length);

        return ret.substring(1);
    }
}
