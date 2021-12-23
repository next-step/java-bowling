package bowling.domain.scores;

import bowling.Pin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralHitScores extends HitScores {

    private static final int MAX_OF_TRY_COUNT = 2;
    private static final int MAX_OF_SCORE = 10;


    public GeneralHitScores() {
        this(new ArrayList<>());
    }

    public GeneralHitScores(int... pins) {
        this(toPins(pins));
    }

    public GeneralHitScores(Pin... pins) {
        this(Arrays.asList(pins));
    }

    public GeneralHitScores(List<Pin> pins) {
        super(pins);

        validHitScores();
    }

    @Override
    public HitScores add(int hitCount) {
        this.hitPins.add(Pin.of(hitCount));

        validHitScores();

        return new GeneralHitScores(this.hitPins);
    }


    @Override
    public boolean isClosed() {
        if (hitPins.size() == MAX_OF_TRY_COUNT) {
            return true;
        }

        return containStrike();
    }


    private void validHitScores() {
        if (hitPins.size() > MAX_OF_TRY_COUNT) {
            throw new IllegalArgumentException(String.format("투구 휫수는 %d개 이상일 수 없어요.",
                MAX_OF_TRY_COUNT));
        }

        if (sumScore() > MAX_OF_SCORE) {
            throw new IllegalArgumentException(
                String.format("Hit 핀의 갯수는 %d개를 넘길 수 없어요.", MAX_OF_SCORE));
        }
    }
}
