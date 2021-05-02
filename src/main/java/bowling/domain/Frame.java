package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.StringUtils;

public class Frame {

  public static final String DELIMITER = "|";
  protected List<Pitching> pitchingList;
  protected int number;

  public Frame() {
    this.pitchingList = new ArrayList<>();
    pitchingList.add(new Pitching());
  }

  public String play(int hitCount) {
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

  public String result() {
    List<String> scoreResult = IntStream.rangeClosed(1, lastPitchingIndex())
        .mapToObj(i -> getScore(i))
        .filter(score -> !StringUtils.isBlank(score))
        .collect(Collectors.toList());
    return StringUtils.join(scoreResult, DELIMITER);
  }

  private String getScore(int i) {
    if (pitchingList.get(i).leftPins() == 10) {
      return "";
    }
    Result result = pitchingList.get(i).result();
    if (result.isNotMiss()) {
      return result.getMark();
    }
    return String.valueOf(getHitScore(i));
  }

  private int getHitScore(int i) {
    return pitchingList.get(i - 1).leftPins() - pitchingList.get(i).leftPins();
  }

}
