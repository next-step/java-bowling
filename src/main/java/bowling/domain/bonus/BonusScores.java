package bowling.domain.bonus;

import bowling.domain.score.Scores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BonusScores {
    private List<BonusScore> bonusScores;

    public BonusScores() {
        this(new ArrayList<>());
    }

    private BonusScores(List<BonusScore> bonusScores) {
        this.bonusScores = bonusScores;
    }

    public void addBonusScore(Scores scores, int frameIndex) {
        if (scores.isStrike()) {
            bonusScores.add(BonusScore.strikeBonusScore(frameIndex));
        }

        if (scores.isSpare()) {
            bonusScores.add(BonusScore.spareBonusScore(frameIndex));
        }
    }

    public void addBonusPoint(int point) {
        bonusScores.stream()
                .filter(BonusScore::isAvailableAddBonusPoint)
                .forEach(bonusScore -> bonusScore.addPoint(point));
    }

    public BonusScores findAvailableAddBonusScores() {
        return bonusScores.stream()
                .filter(BonusScore::isAvailableAddBonusPoint)
                .collect(Collectors.collectingAndThen(Collectors.toList(), BonusScores::new));
    }

    public BonusScore findBonusScores(int frameIndex) {
        return bonusScores.stream()
                .filter(bonusScore -> bonusScore.equalsByFrameIndex(frameIndex))
                .findFirst()
                .orElse(BonusScore.noneBonusScores(frameIndex));
    }

    public boolean isAvailableAddBonusScores(int frameIndex) {
        return bonusScores.stream()
                .anyMatch(bonusScore -> bonusScore.isAvailableAddBonusPoint() && bonusScore.equalsByFrameIndex(frameIndex));
    }
}
