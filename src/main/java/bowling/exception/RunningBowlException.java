package bowling.exception;

public class RunningBowlException extends RuntimeException{
    private static final String RUNNING_BOWL_MESSAGE = "준비 상태입니다.";
    public RunningBowlException(){
        super(RUNNING_BOWL_MESSAGE);
    }
}
