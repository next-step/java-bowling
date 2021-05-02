package bowling.domain;

public class NormalFrame extends Frame {

  private static final int MIN = 1;
  private static final int MAX = 9;

  public NormalFrame(int number) {
    super();
    if (number < MIN || number > MAX) {
      throw new IllegalArgumentException("1~9 프레임여야 한다.");
    }
    this.number = number;
  }

  public NormalFrame() {
    this(1);
  }

  public NormalFrame next() {
    return new NormalFrame(this.number + 1);
  }

  public FinalFrame last() {
    return new FinalFrame();
  }

  public int number() {
    return number;
  }

}
