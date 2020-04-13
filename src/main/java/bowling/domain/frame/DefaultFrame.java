package bowling.domain.frame;

import bowling.domain.bonusscore.BonusScore;
import bowling.domain.bonusscore.NoneBonus;
import bowling.domain.bonusscore.SpareBonus;
import bowling.domain.bonusscore.StrikeBonus;
import bowling.domain.score.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 프레임은 점수를 기록하고 다음 프레임을 생성하는 책임을 가진다.
 */
public class DefaultFrame implements Frame {
    private Scores scores;
    private List<BonusScore> bonusScores;

    private DefaultFrame(Scores scores, List<BonusScore> bonusScores) {
        this.scores = scores;
        this.bonusScores = bonusScores;
    }

    public static DefaultFrame first() {
        return new DefaultFrame(new Scores(), new ArrayList<>());
    }

    public LastFrame lastFrame(int frameIndex) {
        bonusScores.add(createBonusScore(frameIndex));
        return new LastFrame(bonusScores());
    }

    public DefaultFrame nextFrame(int frameIndex) {
        bonusScores.add(createBonusScore(frameIndex));
        return new DefaultFrame(new Scores(), bonusScores());
    }

    private BonusScore createBonusScore(int frameIndex) {
        if (scores.isStrike(0)) {
            return new StrikeBonus(frameIndex);
        }
        if (scores.isSpare(1)) {
            return new SpareBonus(frameIndex);
        }
        return new NoneBonus();
    }

    private List<BonusScore> bonusScores() {
        return bonusScores.stream()
                .filter(BonusScore::isAddable)
                .collect(Collectors.toList());
    }

    @Override
    public void addScore(int point) {
        if (scores.currentPoint() + point > 10) {
            throw new IllegalArgumentException("한 프레임의 포인트는 10점을 넘을수 없습니다.");
        }
        this.scores.add(Score.defaultScore(scores, point));
        addBonusScore(point);
    }

    private void addBonusScore(int score) {
        bonusScores.stream()
                .filter(BonusScore::isAddable)
                .forEach(bonusScore -> bonusScore.add(score));
    }

    @Override
    public boolean isPlayable() {
        if (CollectionUtils.isEmpty(scores.getScores())) {
            return false;
        }
        if (scores.isStrike(0)) {
            return false;
        }
        return scores.size() < 2;
    }

    @Override
    public String getScore(int scoreIndex) {
        return scores.pointToScore(scoreIndex);
    }

    @Override
    public int scoreSize(){
        return scores.size();
    }
}
