package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NormalFrame extends Frame {

    public NormalFrame(List<Score> scores) {
        this.scores = new NormalScores(scores);
    }

    public NormalFrame() {
        this.scores = new NormalScores();
    }

    @Override
    public Optional<Integer> frameScore() {
        if (scores.getScores().isEmpty() || !isFinished()) {
            return Optional.empty();
        }

        Optional<Integer> frameScore = Optional.of(scores.transSpareScores()
                .stream()
                .mapToInt(score -> score.getScore())
                .sum());

        if (scores.getScores().contains(Score.SPARE)) {
            frameScore = getBonusScoreSpare(frameScore);
        }
        if (scores.getScores().contains(Score.STRIKE)) {
            frameScore = getBonusScoreStrike(frameScore);
        }
        return frameScore;
    }

    @Override
    public Optional<List<Score>> getTwoScores() {
        if (this.scores.getScores().size() == 2) {
            return Optional.of(this.scores.transSpareScores());
        }
        if (this.scores.getScores().contains(Score.STRIKE) && nextFrame.getScores().size() >= 1) {
            List<Score> result = new ArrayList<>(scores.transSpareScores());
            result.add(nextFrame.scores.getScores().get(0));
            return Optional.of(result);
        }
        return Optional.empty();
    }

    public Optional<Integer> getBonusScoreSpare(Optional<Integer> frameScore) {
        if (nextFrame.getOneScore().isPresent()) {
            int bonusScore = nextFrame.getOneScore()
                    .get()
                    .getScore();
            return Optional.of(frameScore.get() + bonusScore);
        }
        return Optional.empty();
    }

    public Optional<Integer> getBonusScoreStrike(Optional<Integer> frameScore) {
        if (nextFrame.getTwoScores().isPresent()) {
            int bonusScore = nextFrame.getTwoScores()
                    .get()
                    .stream()
                    .mapToInt(score -> score.getScore())
                    .sum();
            return Optional.of(frameScore.get() + bonusScore);
        }
        return Optional.empty();
    }


}