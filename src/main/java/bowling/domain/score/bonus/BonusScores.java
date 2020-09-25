package bowling.domain.score.bonus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

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

    public List<BonusScore> getBonusScores() {
        return Collections.unmodifiableList(bonusScores);
    }

    public BonusScores findAddableBonusScores() {
        return bonusScores.stream()
                .filter(BonusScore::isAddable)
                .collect(collectingAndThen(toList(), BonusScores::new));
    }

    public void addBonusPin(int pin) {
        bonusScores.stream()
                .filter(BonusScore::isAddable)
                .forEach(bonusScore -> bonusScore.add(pin));
    }

    public boolean isExistBonusScore(int frameIndex) {
        return bonusScores.stream()
                .anyMatch(bonusScore -> bonusScore.isEqualFrameIndex(frameIndex));
    }
}
