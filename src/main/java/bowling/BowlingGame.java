package bowling;

public class BowlingGame {

  Frame frame = NormalFrame.first();

  public Frame roll(int countOfPin) {
    frame = frame.roll(countOfPin);
    return frame;
  }

  public int currentFrameNo() {
    return frame.getFrameNo();
  }

}
