package bowling.domain.scores;

import bowling.Pin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralHitScores extends HitScores {

    private static final int MAX_SCORE_SIZE = 2;
    private static final int MAX_OF_SCORE = 20;


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

        if (pins.size() > MAX_SCORE_SIZE) {
            throw new IllegalArgumentException(String.format("점수는 %d개 이상일 수 없어요.", MAX_SCORE_SIZE));
        }

        if (sumScore() > MAX_OF_SCORE) {
            throw new IllegalArgumentException(String.format("라운드는 %d점을 넘길 수 없어요.", MAX_OF_SCORE));
        }
    }

    @Override
    public HitScores add(int hitCount) {
        if (hitPins.size() >= MAX_SCORE_SIZE) {
            throw new IllegalArgumentException(String.format("점수는 %d개 이상일 수 없어요.", MAX_SCORE_SIZE));
        }

        this.hitPins.add(Pin.of(hitCount));
        return new GeneralHitScores(this.hitPins);
    }

    @Override
    public boolean isClosed() {
        if (hitPins.size() == MAX_SCORE_SIZE) {
            return true;
        }

        return containStrike();
    }

}
