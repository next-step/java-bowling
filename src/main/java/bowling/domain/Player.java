package bowling.domain;

import java.util.List;

public class Player {

  private final String name;
  private final Bowling bowling;

  public Player(String name) {
    this.name = name;
    this.bowling = Bowling.init();
  }

  public static Player ofName(String playerName) {
    return new Player(playerName);
  }

  public String name() {
    return name;
  }

  public boolean isNextPlayer(String name) {
    return this.name.equals(name);
  }

  public void roll(int pins) {
    bowling.roll(pins);
  }

  public boolean isPlayingFrameOf(int current) {
    return bowling.nextFrame() == current;
  }

  public List<String> symbols() {
    return bowling.symbols();
  }

  public List<Score> scores() {
    return bowling.scores();
  }

  @Override
  public String toString() {
    return "Player{" +
        "name='" + name + '\'' +
        ", bowling=" + bowling +
        '}';
  }
}
