package bowling.domain.bonusscore;

import java.util.ArrayList;
import java.util.List;

public class StrikeBonus implements BonusScore {
    private static final int ADDITIONAL_COUNT = 2;
    private static final int DEFAULT_POINT = 0;

    private final List<Integer> bonusScores;
    private final int frameIndex;

    public StrikeBonus(int frameIndex) {
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

    @Override
    public int getBonusPoint(int frameIndex) {
        if (this.frameIndex == frameIndex) {
            return bonusScores.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
        }
        return DEFAULT_POINT;
    }
}
