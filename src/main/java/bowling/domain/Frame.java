package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frame {

  protected List<Pitching> pitchingList;
  protected int number;

  public Frame() {
    this.pitchingList = new ArrayList<>();
    pitchingList.add(new Pitching());
  }

  public List<String> play(int hitCount) {
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

  public List<String> result() {
    List<Integer> hitCounts = getHitCounts();
    return pitchingList.stream()
        .filter(pitching -> pitching.result().isNotNone())
        .map(pitching -> getScore(hitCounts, pitching))
        .collect(Collectors.toList());
  }

  private List<Integer> getHitCounts() {
    List<Integer> pinsInfo = pitchingList.stream()
        .filter(pitching -> pitching.result().isNotNone())
        .map(Pitching::leftPins)
        .collect(Collectors.toList());

    List<Integer> hitCounts = new ArrayList<>();
    pinsInfo.stream().reduce(Pins.MAX, (a, b) -> {
      int hitCount = a - b;
      hitCounts.add(hitCount);
      return b;
    });
    return hitCounts;
  }

  private String getScore(List<Integer> hitCounts, Pitching pitching) {
    if (pitching.result().isNotMiss()) {
      return pitching.result().getMark();
    }
    return String.valueOf(hitCounts.get(pitching.getTryCount() - 1));
  }

}
