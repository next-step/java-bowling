package bowling_step3.domain;

import bowling_step3.domain.Frame.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {

    private List<Integer> scores;

    public Scores(List<Integer> scores) {
        this.scores = scores;
    }

    public static Scores ofFrames(List<Frame> frames) {
        return new Scores(scoreToList(frames));
    }

    public static Scores sumOf(List<Integer> currentScore) {
        return new Scores(sumScore(currentScore));
    }

    private static List<Integer> scoreToList(List<Frame> frames) {
        return frames.stream()
                .filter(Frame::hasScore)
                .map(frame -> frame.getScore())
                .collect(Collectors.toList());
    }

    private static List<Integer> sumScore(List<Integer> currentScore) {
        List<Integer> scores = new ArrayList<>();
        int sum = 0;
        for (Integer score : currentScore) {
            sum += score;
            scores.add(sum);
        }
        return scores;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public int size() {
        return scores.size();
    }
}
