package bowling.domain.frame;

import bowling.domain.score.LastScores;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.List;

public class LastFrame extends Frame {

    private LastFrame(Scores scores) {
        super(scores);
    }

    public static LastFrame empty() {
        return new LastFrame(LastScores.empty());
    }

    public static LastFrame of(List<Score> scores) {
        return new LastFrame(LastScores.of(scores));
    }

//    public int getFrameNumber() {
//        return frameNumber.getNumber();
//    }
//
//    public LastFrame next() {
//        return new LastFrame(frameNumber.next(), Scores.empty());
//    }
//
//    public void record(int score) {
//        scores = scores.add(score);
//    }
//
//    public boolean isFinished() {
//        return scores.isFinished();
//    }
//
//    public List<Score> getScores() {
//        return scores.getScores();
//    }

//    public Integer calculateScore(Integer previousFrameScore, List<LastFrame> nextFrames) {
//        return scores.calculate(previousFrameScore, getNextScores(nextFrames));
//    }

//    private List<Score> getNextScores(List<LastFrame> nextFrames) {
//        return nextFrames.stream()
//                .flatMap(frame -> frame.getScores().stream())
//                .collect(Collectors.toList());
//    }
}
