package bowling.domain.scores;

import bowling.Pin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalHitScores extends HitScores {

    private static final int ADD_ABLE_SCORE_SIZE = 2;
    private static final int MAX_OF_TRY_COUNT = 3;
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

        if (pins.size() > MAX_OF_TRY_COUNT) {
            throw new IllegalArgumentException(String.format("점수는 %d개 이상일 수 없어요.", MAX_OF_TRY_COUNT));
        }

        if (sumScore() > MAX_OF_SCORE) {
            throw new IllegalArgumentException(String.format("라운드는 %d점을 넘길 수 없어요.", MAX_OF_SCORE));
        }

        if (pins.size() == MAX_OF_TRY_COUNT && isNotRunBonus()) {
            throw new IllegalArgumentException("Strike 또는 Spare 를 치지 않은 경우는, 보너스 라운드가 없어요.");
        }

    }

    @Override
    public HitScores add(int hitCount) {
        hitPins.add(Pin.of(hitCount));

        if (hitPins.size() == MAX_OF_TRY_COUNT && isNotRunBonus()) {
            throw new IllegalArgumentException("Strike 또는 Spare 를 치지 않은 경우는, 보너스 라운드가 없어요.");
        }

        return new FinalHitScores(hitPins);
    }


    @Override
    public boolean isClosed() {
        if (hitPins.size() == MAX_OF_TRY_COUNT) {
            return true;
        }

        return hitPins.size() == ADD_ABLE_SCORE_SIZE && isNotRunBonus();
    }


    private boolean isNotRunBonus() {
        return !(containStrike() || containSpare());
    }
}
