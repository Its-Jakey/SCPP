package slvm;

public class VMException extends RuntimeException {

    public void printSubroutines() {
        for (int i = SLVM.subroutines.size() - 1; i >= 0; i--) {
            System.out.println(SLVM.subroutines.get(i));
        }
    }
    public VMException(String msg) {
        super(msg + ", at pc " + SLVM.pc);
        //printSubroutines();
        //SLVM.logVars("vars.txt");
    }
}
