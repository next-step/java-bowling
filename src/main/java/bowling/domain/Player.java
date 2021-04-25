package bowling.domain;

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
}
