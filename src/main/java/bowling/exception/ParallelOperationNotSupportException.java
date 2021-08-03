package bowling.exception;

public class ParallelOperationNotSupportException extends RuntimeException {
    public ParallelOperationNotSupportException() {
        super("Parallel operation not support");
    }
}
