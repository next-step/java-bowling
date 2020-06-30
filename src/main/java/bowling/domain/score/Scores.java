package bowling.domain.score;

import bowling.domain.dto.ScoreResultDto;
import bowling.utils.ElementFindUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Scores {
    private final static int FIRST_SCORE_INDEX = 0;
    private final static int SECOND_SCORE_INDEX = 1;
    private final static int THIRD_SCORE_INDEX = 2;
    private final static int BONUS_MAX_SCORE = 20;
    private final static int DEFAULT_MAX_SCORE = 10;

    private final List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public void addScore(int point) {
        validateScores(point);
        scores.add(createScore(point));
    }

    private void validateScores(int point) {
        if (firstScore() && totalScore() + point > DEFAULT_MAX_SCORE) {
            throw new IllegalArgumentException("first Score is max 10");
        }

        if (thirdScore() && totalScore() + point > BONUS_MAX_SCORE) {
            throw new IllegalArgumentException("second Score is max 20");
        }
    }

    private Score createScore(int point) {
        if (CollectionUtils.isEmpty(scores)) {
            return ScoreFactory.generateScore(point);
        }

        return ElementFindUtils.findFirstElement(scores).nextScore(point);
    }

    public int totalScore() {
        return scores.stream()
                .mapToInt(Score::getPoint)
                .sum();
    }

    private boolean firstScore() {
        return scores.size() == FIRST_SCORE_INDEX;
    }

    private boolean secondScore() {
        return scores.size() == SECOND_SCORE_INDEX;
    }

    private boolean thirdScore() {
        return scores.size() == THIRD_SCORE_INDEX;
    }

    public boolean isStrike() {
        if (CollectionUtils.isEmpty(scores)) {
            return false;
        }
        return ElementFindUtils.findFirstElement(scores).isStrike();
    }

    public boolean isSpare() {
        if (secondScore() || thirdScore()) {
            return ElementFindUtils.findLastElement(scores).isSpare();
        }
        return false;
    }

    public boolean isStrikeOrSpare() {
        return isStrike() || isSpare();
    }

    public List<ScoreResultDto> convertSoreResultDtos() {
        return scores.stream()
                .map(ScoreResultDto::new)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    public boolean isAvailableAdd() {
        if (CollectionUtils.isEmpty(scores)) {
            return true;
        }

        if (isStrikeOrSpare()) {
            return false;
        }

        return secondScore();
    }

    public boolean isLastFrameAvailableAdd() {
        if (CollectionUtils.isEmpty(scores)) {
            return true;
        }

        if (isStrike()) {
            return secondScore();
        }

        if (isSpare()) {
            return thirdScore();
        }
        return false;
    }
}
