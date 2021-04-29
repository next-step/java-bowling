package bowling.domain;

public class Frame {

  private Pitching first;
  private Pitching second;

  public Frame() {}

  public void play(int hitCount) {
    if (first != null && !first.getResult().isStrike()) {
      playSecondBall(hitCount);
    }
    if (first == null) {
      playFirst(hitCount);
    }
  }

  private void playFirst(int hitCount) {
    this.first = Pitching.firstPitching(hitCount);
  }

  private void playSecondBall(int hitCount) {
    this.second = first.secondPitching(hitCount);
  }

  public int getFirstHit() {
    return first.getHitPins();
  }

  public int getSecondHit() {
    return second.getHitPins();
  }

  public Result getResult() {
    if (first == null) {
      return Result.NONE;
    }
    Result firstResult = first.getResult();
    if (firstResult.isStrike() || second == null) {
      return firstResult;
    }
    return second.getResult();
  }

  public boolean isEnd() {
    if (getResult().isNotMiss()) {
      return true;
    }
    return first != null && second != null;
  }

  public boolean hasBonus() {
    return getResult().isNotMiss();
  }

  public boolean isStrike() {
    return getResult().isStrike();
  }

  public Pitching getFirst() {
    return first;
  }

  public Pitching getSecond() {
    return second;
  }
}
