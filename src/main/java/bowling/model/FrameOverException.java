package bowling.model;

public class FrameOverException extends Exception {

  public FrameOverException() {
    super("다음 프레임 생성 필요");
  }
}
