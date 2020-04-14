package bowling.domain.frame;

import bowling.domain.bonusscore.BonusScore;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import bowling.domain.score.Scores;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 마지막 프레임을 나타내기 위한 객체
 * 마지막 프레임에서 스트라이크, 스페어 일 경우 1구를 더 진행한다.
 */
public class LastFrame implements Frame {
    private static final int STRIKE_POINT = 10;
    private static final int DEFAULT_PLAY_COUNT = 2;
    private static final int BONUS_PLAY_COUNT = 3;
    private static final int FIRST_PLAY = 0;

    private Scores scores;
    private List<BonusScore> bonusScores;

    public LastFrame(List<BonusScore> bonusScores) {
        this.scores = new Scores();
        this.bonusScores = bonusScores;
    }

    @Override
    public void addScore(int point) {
        if (scores.isSecondPlay()) {
            validateSpareScore(point);
        }

        this.scores.add(Score.lastScore(scores, point));
        addBonusScore(point);
    }

    private void validateSpareScore(int point) {
        if (CollectionUtils.isEmpty(scores.getScores())) {
            return;
        }
        if (!scores.isStrike(FIRST_PLAY) && totalPoint(point) > STRIKE_POINT) {
            throw new IllegalArgumentException("1구와 2구의 포인트합은 10점을 넘을수 없습니다.");
        }
    }

    private int totalPoint(int point) {
        return scores.currentPoint() + point;
    }

    private void addBonusScore(int point) {
        bonusScores.stream()
                .filter(BonusScore::isAddable)
                .forEach(bonusScore -> bonusScore.add(point));
    }

    @Override
    public String getScore(int scoreIndex) {
        return scores.pointToScore(scoreIndex);
    }

    @Override
    public boolean isPlayable() {
        if (CollectionUtils.isEmpty(scores.getScores())) {
            return true;
        }
        if (scores.size() < DEFAULT_PLAY_COUNT) {
            return true;
        }

        return scores.size() < BONUS_PLAY_COUNT && scores.hasStrikeOrSpare();
    }

    @Override
    public int scoreSize() {
        return scores.size();
    }

    @Override
    public int getBonusScore(int frameIndex) {
        return bonusScores.stream()
                .mapToInt(bonusScore -> bonusScore.getBonusPoint(frameIndex))
                .sum();
    }
}
