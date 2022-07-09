package slvm;

public class VMException extends RuntimeException {
    public VMException(String msg) {
        super(msg + ", at pc " + SLVM.pc);
    }
}
