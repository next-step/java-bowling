package bowling;

public class BowlingGame {

  public final static int MAX_NUMBER_OF_FRAMES = 10;

  Frames frames = new Frames();

  public void roll(int number) {
    frames.roll(number);
  }

  public boolean requiredBonusFrame() {
    return frames.isOver();
  }

  @Override
  public String toString() {
    return "BowlingGame{" +
        "frames2=" + frames +
        '}';
  }
}
