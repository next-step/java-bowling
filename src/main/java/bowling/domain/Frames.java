package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

  private static final int FINAL_ROUND = 10;
  private final List<Frame> frames;
  private final List<Score> scores;
  private List<Integer> result;
  private Round round;

  public Frames(List<Frame> frames) {
    this.frames = frames;
    this.round = Round.firstRound();
    this.scores = new ArrayList<>();
    this.result = new ArrayList<>();
  }

  public static Frames init() {
    List<Frame> frames = normalFrames();
    frames.add(FinalFrame.of());
    return new Frames(frames);
  }

  private static List<Frame> normalFrames() {
    return IntStream.range(0, 9)
        .mapToObj(Round::new)
        .map(NormalFrame::new)
        .collect(Collectors.toList());
  }

  public void throwBall(int countOfHitPin) {
    Frame frame = frames.get(round() - 1);
    frame.play(countOfHitPin);

    calculateScores(countOfHitPin);
    if (frame.isEndFrame()) {
      initScore(frame);
      round = round.next();
    }
  }

  private void initScore(Frame frame) {
    scores.add(Score.of(frame));
  }

  private void calculateScores(int countOfPins) {
    scores.stream()
        .filter(score -> !score.canCalculateScore())
        .forEach(score -> score.bowl(countOfPins));
  }

  public List<Integer> frameScore() {
    this.result = new ArrayList<>();
    for (int i = 0; i < scores.size(); i++) {
      add(scores.get(i), i);
    }
    return result;
  }

  private void add(Score score, int index) {
    if (score.canCalculateScore()) {
      result.add(sum(index));
    }
  }

  private int sum(int index) {
    return scores.stream()
        .limit(index + 1)
        .map(Score::getScore)
        .reduce(0, Integer::sum);
  }

  public boolean isContinue() {
    return frames.get(FINAL_ROUND - 1).isLastFrame();
  }

  public int round() {
    return round.round();
  }

  public List<Frame> frames() {
    return Collections.unmodifiableList(frames);
  }
}
