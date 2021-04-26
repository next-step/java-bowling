package bowling_step4.domain;

import bowling_step4.domain.frame.Frames;

import java.util.List;
import java.util.Objects;

public class BowlingGame {

  public static final String INVALID_END_PLAY = "더이상 진행 할 수 없습니다.";
  private final Player player;
  private final Frames frames;

  public BowlingGame(String name) {
    this(new Player(name), new Frames());
  }

  public BowlingGame(Player player, Frames frames) {
    this.player = player;
    this.frames = frames;
  }

  public void play(Pin pinCount) {
    if (isEnd()) {
      throw new IllegalArgumentException(INVALID_END_PLAY);
    }

    frames.play(pinCount);
  }

  public boolean isEnd() {
    return frames.isEnd();
  }

  public Frames getFrames() {
    return frames;
  }

  public int getFrameCount() {
    return frames.getIndex();
  }

  public List<Integer> getScore() {
    return frames.getScores();
  }

  public boolean equalToPlayer(Player player) {
    return this.player.equals(player);
  }

  public Player getPlayer() {
    return player;
  }

  public boolean isCurrentFrame(int currentFrameCount) {
    return getFrameCount() == currentFrameCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BowlingGame that = (BowlingGame) o;
    return Objects.equals(player, that.player);
  }

  @Override
  public int hashCode() {
    return Objects.hash(player);
  }


}
