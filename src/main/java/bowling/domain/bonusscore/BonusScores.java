package bowling.domain.bonusscore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 보너스 스코어의 일급 컬렉션
 * 보너스 점수의 추가 및 제어, 합산을 담당한다.
 */
public class BonusScores {
    private final List<BonusScore> bonusScores;

    public BonusScores() {
        this(new ArrayList<>());
    }

    private BonusScores(List<BonusScore> bonusScores) {
        this.bonusScores = bonusScores;
    }

    public void add(BonusScore bonusScore) {
        bonusScores.add(bonusScore);
    }

    public BonusScores findAddableBonusScores() {
        return bonusScores.stream()
                .filter(BonusScore::isAddable)
                .collect(Collectors.collectingAndThen(Collectors.toList(), BonusScores::new));
    }

    public void addBonusPoint(int point) {
        bonusScores.stream()
                .filter(BonusScore::isAddable)
                .forEach(bonusScore -> bonusScore.add(point));
    }

    public boolean isExistBonusScore(int frameIndex) {
        return bonusScores.stream()
                .anyMatch(bonusScore -> bonusScore.isEqualFrameIndex(frameIndex));
    }

    public BonusScore findBonusScore(int frameIndex) {
        return bonusScores.stream()
                .filter(bonusScore -> bonusScore.isEqualFrameIndex(frameIndex))
                .findFirst()
                .orElse(BonusScore.noneBonus(frameIndex));
    }
}
