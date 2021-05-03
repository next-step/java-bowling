package bowling.domain;

public class FinalFrame extends Frame{

  private static final int MAX = 3;

  private int pitchingCount;

  public FinalFrame() {
    super();
    this.number = 10;
    this.pitchingCount = 0;
  }

  @Override
  public String play(int hitCount) {
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
