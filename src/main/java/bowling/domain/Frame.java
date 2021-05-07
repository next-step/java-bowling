package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {

  protected List<Pitching> pitchingList;
  protected int number;

  public Frame() {
    this.pitchingList = new ArrayList<>();
    pitchingList.add(new Pitching());
  }

  public List<Integer> play(int hitCount) {
    Pitching pitching = lastPitching();
    Pitching play = pitching.play(hitCount);
    pitchingList.add(play);
    return result();
  }

  public boolean isEnd() {
    return lastPitching().isEnd();
  }

  public int number() {
    return number;
  }

  public Pitching lastPitching() {
    return pitchingList.get(lastPitchingIndex());
  }

  private int lastPitchingIndex() {
    return pitchingList.size() - 1;
  }

  public List<Integer> result() {
    List<Integer> hitCounts = getHitCounts();
    return hitCounts;
  }

  private List<Integer> getHitCounts() {
    List<Integer> hitCounts = new ArrayList<>();
    pitchingList.stream().filter(pitching -> pitching.result().isNotNone())
        .map(Pitching::leftPins)
        .reduce(Pins.MAX, (first, second) -> {
          first = initialize(first);
          int count = first - second;
          hitCounts.add(count);
          return second;
        });
    return hitCounts;
  }

  private int initialize(int leftPinsCount) {
    if (leftPinsCount == Pins.MIN) {
      leftPinsCount = Pins.MAX;
    }
    return leftPinsCount;
  }

}
