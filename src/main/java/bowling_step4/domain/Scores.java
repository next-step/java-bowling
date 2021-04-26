package bowling_step4.domain;


import bowling_step4.domain.frame.Frame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {
  private final List<Score> scores;

  public Scores() {
    this(new ArrayList<>());
  }

  public Scores(final List<Score> scores) {
    this.scores = scores;
  }

  public void accumulate(final Frame frame) {
    Score score = frame.getScore();
    if (score.isNotAddable() ) {
      return;
    }

    if (scores.isEmpty()) {
      scores.add(score);
      return;
    }

    Score prevScore = scores.get(scores.size() - 1);
    Score nextScore = prevScore.add(score);
    scores.add(nextScore);
  }

  public List<Integer> getScores() {
    return scores.stream()
        .map(score -> score.getScore())
        .collect(Collectors.toList());
  }
}