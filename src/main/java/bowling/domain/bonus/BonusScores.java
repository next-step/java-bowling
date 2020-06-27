package bowling.domain.bonus;

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

    public void addBonusScore(BonusScore bonusScore) {
        bonusScores.add(bonusScore);
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

    public boolean isAvailableAdd() {
        return bonusScores.stream()
                .filter(BonusScore::isAvailableAddBonusPoint)
                .findAny()
                .isPresent();
    }
}
