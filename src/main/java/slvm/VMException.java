package slvm;

import compiler.Compiler;

public class VMException extends RuntimeException {

    public VMException(String msg, SLVM vm) {
        super(msg + "\n    at pc " + vm.pc + " (near " + getLine(vm.pc) + ")" + stackTrace(vm));
    }

    private static String stackTrace(SLVM vm) {
        StringBuilder ret = new StringBuilder();

        for (Integer pc : vm.stack.stream().toList()) {
            ret.append("\n    at pc ").append(pc).append(" (near ").append(getLine(pc)).append(")");
        }
        return ret.toString();
    }

    private static String getLine(int pc) {
        int tmp = pc;

        while (Compiler.PCLines.get(tmp) == null)
            tmp--;
        return Compiler.PCLines.get(tmp).file() + ":" + Compiler.PCLines.get(tmp).line();
    }

    public VMException(String msg) {
        super(msg);
    }
}
