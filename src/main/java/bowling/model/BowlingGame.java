package bowling.model;

import java.util.List;

public class BowlingGame {

  public final static int MAX_NUMBER_OF_FRAMES = 10;

  private Frames frames = new Frames();
  private int bonusCnt;
  private PlayerName playerName;

  private BowlingGame(PlayerName playerName) {
    this.playerName = playerName;
  }

  public static BowlingGame createWith(String playerName) {
    return new BowlingGame(new PlayerName(playerName));
  }

  public String getPlayerName() {
    return playerName.getValue();
  }

  public List<FrameDTO> getFramesDTO() {
    return new FramesDTO(frames.getFrames()).getFrames();
  }

  public int getCurrentFrameNum() {
    if (frames.isCurrentFrameOver()) {
      return frames.getSize() + 1;
    }

    return frames.getSize();
  }

  public void roll(int number) {
    frames.roll(number);
  }

  public void initBonusCnt() {
    bonusCnt = frames.getFrames().get(MAX_NUMBER_OF_FRAMES - 1).getIndexOfScoredFrames().size() - 1;
  }

  public void bonusRoll(int number) {
    frames.bonusRoll(number);
    bonusCnt--;
  }

  public boolean hasBonus() {
    return 0 < bonusCnt;
  }

  public boolean requiredNormalFrame() {
    return !frames.isOver();
  }

  @Override
  public String toString() {
    return "BowlingGame{" +
        "frames2=" + frames +
        '}';
  }
}
