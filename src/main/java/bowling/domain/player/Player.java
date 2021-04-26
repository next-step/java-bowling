package bowling.domain.player;

import bowling.domain.frame.Frames;

public class Player {

  private final Name name;
  private final Frames frames;

  public Player(String name) {
    this.name = new Name(name);
    this.frames = Frames.init();
  }

  public Frames frames() {
    return frames;
  }

  public boolean isEndFrame(int currFrame) {
    return frames.isEndFrame(currFrame);
  }

  public void throwBall(int countOfHitPin, int currFrame) {
    frames.throwBall(countOfHitPin, currFrame);
  }

  public Name name() {
    return name;
  }
}
