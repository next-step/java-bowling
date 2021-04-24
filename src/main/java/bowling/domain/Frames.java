package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

  private static final int TOTAL_FRAME_SIZE = 10;
  private final List<Frame> frames;
  private List<Integer> scores;

  public Frames(List<Frame> frames) {
    this.frames = frames;
    this.scores = new ArrayList<>();
  }

  public static Frames init() {
    List<Frame> frames = new ArrayList<>();
    frames.add(NormalFrame.of());
    return new Frames(frames);
  }

  public void throwBall(int countOfHitPin) {
    Frame frame = frames.get(frames.size() - 1);
    frame.play(countOfHitPin);

    calculateScore(countOfHitPin);
    if (frame.isEndFrame()) {
      addFrame(frame);
      frame.initScore();
    }
  }

  private void addFrame(Frame frame) {
    if (frames.size() < TOTAL_FRAME_SIZE) {
      Frame next = frame.next(frames.size());
      frames.add(next);
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
    for (int i = 0; i < frames.size(); i++) {
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
    return frames.get(frames.size() - 1).isLastFrame();
  }

  public int round() {
    return frames.size();
  }

  public List<Frame> frames() {
    return Collections.unmodifiableList(frames);
  }
}
