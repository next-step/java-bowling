package bowling.domain.scores;

import bowling.Pin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalHitScores extends HitScores {

    private static final int ADD_ABLE_SCORE_SIZE = 2;
    private static final int MAX_SCORE_SIZE = 3;
    private static final int MAX_OF_SCORE = 30;

    public FinalHitScores() {
        this(new ArrayList<>());
    }

    public FinalHitScores(int... number) {
        this(toPins(number));
    }

    public FinalHitScores(Pin... pins) {
        this(Arrays.asList(pins));
    }

    public FinalHitScores(List<Pin> pins) {
        super(pins);

        if (pins.size() > MAX_SCORE_SIZE) {
            throw new IllegalArgumentException(String.format("점수는 %d개 이상일 수 없어요.", MAX_SCORE_SIZE));
        }

        if (sumScore() > MAX_OF_SCORE) {
            throw new IllegalArgumentException(String.format("라운드는 %d점을 넘길 수 없어요.", MAX_OF_SCORE));
        }

        if (pins.size() == MAX_SCORE_SIZE && isNotRunBonus()) {
            throw new IllegalArgumentException("Strike 또는 Spare 를 치지 않은 경우는, 보너스 라운드가 없어요.");
        }

    }

    @Override
    public HitScores add(int hitCount) {
        if (hitPins.size() == ADD_ABLE_SCORE_SIZE && isNotRunBonus()) {
            throw new IllegalArgumentException("Strike 또는 Spare 를 치지 않은 경우는, 보너스 라운드가 없어요.");
        }

        hitPins.add(Pin.of(hitCount));
        return new FinalHitScores(hitPins);
    }


    @Override
    public boolean isClosed() {
        if (hitPins.size() == MAX_SCORE_SIZE) {
            return true;
        }

        return hitPins.size() == ADD_ABLE_SCORE_SIZE && isNotRunBonus();
    }


    private boolean isNotRunBonus() {
        return !(containStrike() || containSpare());
    }
}
