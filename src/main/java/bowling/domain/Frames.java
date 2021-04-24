package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

  private static final int FINAL_ROUND = 10;
  private final List<Frame> frames;
  private int frameRound;
  private List<Integer> scores;

  public Frames(List<Frame> frames) {
    this.frames = frames;
    this.frameRound = 1;
    this.scores = new ArrayList<>();
  }

  public static Frames init() {
    List<Frame> frames = normalFrames();
    frames.add(FinalFrame.of());
    return new Frames(frames);
  }

  private static List<Frame> normalFrames() {
    return IntStream.range(0, 9)
        .mapToObj(i -> new NormalFrame())
        .collect(Collectors.toList());
  }

  public void throwBall(int countOfHitPin) {
    Frame frame = frames.get(frameRound - 1);
    frame.play(countOfHitPin);

    calculateScore(countOfHitPin);
    if (frame.isEndFrame()) {
      frame.initScore();
      frameRound++;
    }
  }

  private void calculateScore(int countOfPins) {
    frames.stream()
        .filter(Frame::hasScore)
        .map(frame -> frame.score)
        .filter(score -> !score.canCalculateScore())
        .forEach(score -> score.bowl(countOfPins));
  }

  public List<Integer> frameScores() {
    this.scores = new ArrayList<>();
    for (int i = 0; i < FINAL_ROUND; i++) {
      addScore(frames.get(i), i);
    }
    return scores;
  }

  private void addScore(Frame frame, int index) {
    Score score = frame.score;
    if (frame.hasScore() && score.canCalculateScore()) {
      scores.add(accumulateScores(index));
    }
  }

  private int accumulateScores(int index) {
    return frames.stream()
        .limit(index + 1)
        .map(frame -> frame.score)
        .map(Score::getScore)
        .reduce(0, Integer::sum);
  }

  public boolean isContinue() {
    return frames.get(FINAL_ROUND - 1).isLastFrame();
  }

  public int round() {
    return frameRound;
  }

  public List<Frame> frames() {
    return Collections.unmodifiableList(frames);
  }
}
