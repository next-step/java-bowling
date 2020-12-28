package bowling_step3.exception;

public class FrameFinishedScoreCreateException extends IllegalStateException {
    public FrameFinishedScoreCreateException(){
        super("프레임이 종료된 후에 점수를 생성 할 수 있습니다.");
    }
}
