package slvm.optimizer;

import java.util.Arrays;
import java.util.HashMap;

public class Optimizer {
    private static HashMap<String, Integer> vars;

    private static String handleVar(String name) {
        if (vars.containsKey(name))
            return String.valueOf(vars.get(name));
        vars.put(name, vars.size());
        return String.valueOf(vars.size() - 1);
    }

    public static String[] optimize(String[] assembledInstructions_) {
        vars = new HashMap<>();

        String[] ret = new String[assembledInstructions_.length];
        String[] assembledInstructions = Arrays.stream(assembledInstructions_).map(inst -> inst.substring(1, inst.length() - 1)).toArray(String[]::new);

        for (int i = 0; i < assembledInstructions.length; i++) {
            String instruction = assembledInstructions[i];

            switch (instruction) {
                case "ldi" -> {
                    ret[i++] = "ldi";
                    ret[i] = assembledInstructions[i];
                }
                case "loadAtVar" -> {
                    ret[i++] = "loadAtVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "storeAtVar" -> {
                    ret[i++] = "storeAtVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "jts" -> {
                    ret[i++] = "jts";
                    ret[i] = assembledInstructions[i];
                }
                case "ret" -> ret[i] = "ret";
                case "addWithVar" -> {
                    ret[i++] = "addWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "subWithVar" -> {
                    ret[i++] = "subWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "mulWithVar" -> {
                    ret[i++] = "mulWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "divWithVar" -> {
                    ret[i++] = "divWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "bitwiseLsfWithVar" -> {
                    ret[i++] = "bitwiseLsfWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "bitwiseRsfWithVar" -> {
                    ret[i++] = "bitwiseRsfWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "bitwiseAndWithVar" -> {
                    ret[i++] = "bitwiseAndWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "bitwiseOrWithVar" -> {
                    ret[i++] = "bitwiseOrWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "modWithVar" -> {
                    ret[i++] = "modWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "print" -> ret[i] = "print";
                case "println" -> ret[i] = "println";
                case "jmp" -> {
                    ret[i++] = "jmp";
                    ret[i] = assembledInstructions[i];
                }
                case "jt" -> {
                    ret[i++] = "jt";
                    ret[i] = assembledInstructions[i];
                }
                case "jf" -> {
                    ret[i++] = "jf";
                    ret[i] = assembledInstructions[i];
                }
                case "boolAndWithVar" -> {
                    ret[i++] = "boolAndWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "boolOrWithVar" -> {
                    ret[i++] = "boolOrWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "boolEqualWithVar" -> {
                    ret[i++] = "boolEqualWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "largerThanOrEqualWithVar" -> {
                    ret[i++] = "largerThanOrEqualWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "smallerThanOrEqualWithVar" -> {
                    ret[i++] = "smallerThanOrEqualWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "boolNotEqualWithVar" -> {
                    ret[i++] = "boolNotEqualWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "smallerThanWithVar" -> {
                    ret[i++] = "smallerThanWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "largerThanWithVar" -> {
                    ret[i++] = "largerThanWithVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "putPixel" -> {
                    ret[i++] = "putPixel";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "putLine" -> {
                    ret[i++] = "putLine";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    ret[i + 2] = handleVar(assembledInstructions[i + 2]);
                    ret[i + 3] = handleVar(assembledInstructions[i + 3]);
                    i += 3;
                }
                case "putRect" -> {
                    ret[i++] = "putRect";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    ret[i + 2] = handleVar(assembledInstructions[i + 2]);
                    ret[i + 3] = handleVar(assembledInstructions[i + 3]);
                    i += 3;
                }
                case "setColor" -> {
                    ret[i++] = "setColor";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "clg" -> ret[i] = "clg";
                case "done" -> ret[i] = "done";
                case "malloc" -> {
                    ret[i++] = "malloc";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "round" -> {
                    ret[i++] = "round";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "floor" -> {
                    ret[i++] = "floor";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "ceil" -> {
                    ret[i++] = "ceil";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "cos" -> {
                    ret[i++] = "cos";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "sin" -> {
                    ret[i++] = "sin";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "sqrt" -> {
                    ret[i++] = "sqrt";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "atan2" -> {
                    ret[i++] = "atan2";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "mouseDown" -> ret[i] = "mouseDown";
                case "mouseX" -> ret[i] = "mouseX";
                case "mouseY" -> ret[i] = "mouseY";
                case "sleep" -> {
                    ret[i++] = "sleep";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "drawText" -> {
                    ret[i++] = "drawText";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "loadAtVarWithOffset" -> {
                    ret[i++] = "loadAtVarWithOffset";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "storeAtVarWithOffset" -> {
                    ret[i++] = "storeAtVarWithOffset";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "isKeyPressed" -> {
                    ret[i++] = "isKeyPressed";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "createColor" -> {
                    ret[i++] = "createColor";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    ret[i + 2] = handleVar(assembledInstructions[i + 2]);
                    i += 2;
                }
                case "charAt" -> {
                    ret[i++] = "charAt";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "sizeOf" -> {
                    ret[i++] = "sizeOf";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "contains" -> {
                    ret[i++] = "contains";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "join" -> {
                    ret[i++] = "join";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "setStrokeWidth" -> {
                    ret[i++] = "setStrokeWidth";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "inc" -> {
                    ret[i++] = "inc";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "dec" -> {
                    ret[i++] = "dec";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "graphicsFlip" -> ret[i] = "graphicsFlip";
                case "newLine" -> ret[i] = "newLine";
                case "ask" -> {
                    ret[i++] = "ask";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "setCloudVar" -> {
                    ret[i++] = "setCloudVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "getCloudVar" -> {
                    ret[i++] = "getCloudVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "indexOfChar" -> {
                    ret[i++] = "indexOfChar";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "goto" -> {
                    ret[i++] = "goto";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "imalloc" -> {
                    ret[i++] = "imalloc";
                    ret[i] = assembledInstructions[i];
                }
                case "getValueAtPointer" -> {
                    ret[i++] = "getValueAtPointer";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "setValueAtPointer" -> {
                    ret[i++] = "setValueAtPointer";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "runtimeMillis" -> ret[i] = "runtimeMillis";
                case "free" -> {
                    ret[i++] = "free";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "getVarAddress" -> {
                    ret[i++] = "getVarAddress";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "setVarAddress" -> {
                    ret[i++] = "setVarAddress";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "copyVar" -> {
                    ret[i++] = "copyVar";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "incA" -> ret[i] = "incA";
                case "decA" -> ret[i] = "decA";
                case "arrayBoundsCheck" -> {
                    ret[i++] = "arrayBoundsCheck";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    i++;
                }
                case "getValueAtPointerOfA" -> ret[i] = "getValueAtPointerOfA";
                case "stackPushA" -> ret[i] = "stackPushA";
                case "stackPopA" -> ret[i] = "stackPopA";
                case "stackPush" -> {
                    ret[i++] = "stackPush";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "stackPop" -> {
                    ret[i++] = "stackPop";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "stackPeekA" -> ret[i] = "stackPeekA";
                case "stackPeek" -> {
                    ret[i++] = "stackPeek";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "stackInc" -> ret[i] = "stackInc";
                case "stackDec" -> ret[i] = "stackDec";
                case "stackAdd" -> ret[i] = "stackAdd";
                case "stackSub" -> ret[i] = "stackSub";
                case "stackMul" -> ret[i] = "stackMul";
                case "stackDiv" -> ret[i] = "stackDiv";
                case "stackBitwiseLsf" -> ret[i] = "stackBitwiseLsf";
                case "stackBitwiseRsf" -> ret[i] = "stackBitwiseRsf";
                case "stackBitwiseAnd" -> ret[i] = "stackBitwiseAnd";
                case "stackBitwiseOr" -> ret[i] = "stackBitwiseOr";
                case "stackMod" -> ret[i] = "stackMod";
                case "stackBoolAnd" -> ret[i] = "stackBoolAnd";
                case "stackBoolOr" -> ret[i] = "stackBoolOr";
                case "stackBoolEqual" -> ret[i] = "stackBoolEqual";
                case "stackLargerThanOrEqual" -> ret[i] = "stackLargerThanOrEqual";
                case "stackSmallerThanOrEqual" -> ret[i] = "stackSmallerThanOrEqual";
                case "stackBoolNotEqual" -> ret[i] = "stackBoolNotEqual";
                case "stackSmallerThan" -> ret[i] = "stackSmallerThan";
                case "stackLargerThan" -> ret[i] = "stackLargerThan";
                case "conditionalValueSet" -> {
                    ret[i++] = "conditionalValueSet";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "toAsciiValue" -> {
                    ret[i++] = "toAsciiValue";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "fromAsciiValue" -> {
                    ret[i++] = "fromAsciiValue";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "bitwiseNot" -> {
                    ret[i++] = "bitwiseNot";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "stackPushI" -> {
                    ret[i++] = "stackPushI";
                    ret[i] = assembledInstructions[i];
                }
                case "stackJoin" -> ret[i] = "stackJoin";
                case "fillCircle" -> {
                    ret[i++] = "fillCircle";
                    ret[i] = handleVar(assembledInstructions[i]);
                    ret[i + 1] = handleVar(assembledInstructions[i + 1]);
                    ret[i + 2] = handleVar(assembledInstructions[i + 2]);
                    i += 2;
                }
                case "playSound" -> {
                    ret[i++] = "playSound";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "startSound" -> {
                    ret[i++] = "startSound";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "stopSounds" -> ret[i] = "stopSounds";
                case "setVolume" -> {
                    ret[i++] = "setVolume";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                case "setPitch" -> {
                    ret[i++] = "setPitch";
                    ret[i] = handleVar(assembledInstructions[i]);
                }
                default -> throw new IllegalStateException("Unknown instruction: " + instruction + ", at pc: " + i);
            }
        }

        return ret;
    }
}
