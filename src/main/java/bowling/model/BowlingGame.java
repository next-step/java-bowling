package bowling.model;

import java.util.List;

public class BowlingGame {

  public final static int MAX_NUMBER_OF_FRAMES = 10;

  private Frames frames = new Frames();
  private int bonusCount;
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

  public int getCurrentFrameNumber() {
    if (frames.isCurrentFrameOver()) {
      return frames.getSize() + 1;
    }

    return frames.getSize();
  }

  public List<Integer> getScores() {
    return frames.getScores();
  }

  public void roll(int number) {
    frames.roll(number);
  }

  public void initBonusCount() {
    bonusCount = frames.getSizeOfBonusFrames();
  }

  public void bonusRoll(int number) {
    frames.bonusRoll(number);
    bonusCount--;
  }

  public boolean hasBonus() {
    return 0 < bonusCount;
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
