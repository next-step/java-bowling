package bowling.domain;

import java.util.List;

public class FinalFrame extends Frame{

  private static final int MAX = 3;
  private static final int FINAL_FRAME_NUMBER = 10;

  private int pitchingCount;

  public FinalFrame() {
    super();
    this.number = FINAL_FRAME_NUMBER;
    this.pitchingCount = 0;
  }

  @Override
  public List<Integer> play(int hitCount) {
    pitchingCount++;
    return super.play(hitCount);
  }

  @Override
  public boolean isEnd() {
    if (lastPitching().isClear()) {
      return !addBonus();
    }
    return super.isEnd();
  }

  private boolean addBonus() {
    if (hasBonus()) {
      pitchingList.add(new Pitching());
      return true;
    }
    return false;
  }

  private boolean hasBonus() {
    if (pitchingCount == MAX) {
      return false;
    }
    return lastPitching().result().isNotMiss();
  }
}
