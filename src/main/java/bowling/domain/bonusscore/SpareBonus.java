package bowling.domain.bonusscore;

import java.util.ArrayList;
import java.util.List;

public class SpareBonus implements BonusScore {
    private static final int ADDITIONAL_COUNT = 1;

    private List<Integer> bonusScores;
    private int frameIndex;

    public SpareBonus(int frameIndex) {
        this.bonusScores = new ArrayList<>();
        this.frameIndex = frameIndex;
    }

    @Override
    public void add(int score) {
        bonusScores.add(score);
    }

    @Override
    public boolean isAddable() {
        return bonusScores.size() < ADDITIONAL_COUNT;
    }
}
