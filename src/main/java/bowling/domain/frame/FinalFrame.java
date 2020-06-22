package bowling.domain.frame;

import bowling.domain.dto.ScoreResultDto;
import bowling.domain.score.Gutter;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreFactory;
import bowling.domain.score.Scores;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FinalFrame extends Frame {
    private final static int BONUS_MAX_SCORE = 20;
    private final static int DEFAULT_MAX_SCORE = 10;

    private Score bonusScore;

    public FinalFrame() {
        super(new Scores());
    }

    @Override
    public void addPoint(int point) {
        validateScores();
        if (scores.isStrikeOrSpare()) {
            bonusScore = ScoreFactory.generateScore(point);
            return;
        }
        scores.addScore(point);
    }

    @Override
    public void validateScores() {
        if (isBonusGame() && scores.totalScore() > BONUS_MAX_SCORE) {
            throw new IllegalArgumentException("third frame score less than 20");
        }

        if (!isBonusGame() && scores.totalScore() > DEFAULT_MAX_SCORE) {
            throw new IllegalArgumentException("second frame score less than 10");
        }

    }

    @Override
    public boolean availablePlay() {
        if (isBonusGame()) {
            return Objects.isNull(bonusScore);
        }

        if (scores.isStrike()) {
            return scores.firstScore();
        }

        if (scores.isSpare()) {
            return scores.secondScore();
        }

        return scores.firstScore();
    }

    private boolean isBonusGame() {
        return scores.isStrike() || scores.isSpare();
    }

    @Override
    public int totalScore() {
        return Optional.ofNullable(bonusScore)
                .map(bonusScore -> bonusScore.getPoint())
                .orElse(0) + scores.totalScore();
    }

    @Override
    public List<ScoreResultDto> getScoreResultDtos() {
        List<ScoreResultDto> scoreResultDtos = scores.convertSoreResultDtos();
        if (Objects.nonNull(bonusScore)) {
            scoreResultDtos = new ArrayList<>(scoreResultDtos);
            scoreResultDtos.add(new ScoreResultDto(bonusScore));
        }
        return new ArrayList<>(scoreResultDtos);
    }
}
