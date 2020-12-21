package bowling.domain.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created By mand2 on 2020-12-21.
 */
public class Score {

    public final static int MAX_SCORE = 10;
    public final static int FIRST_PITCH = 1;
    public final static int SECOND_PITCH = 2;

    public static final String OVER_SCORE_IN_EACH_FRAME = "한 프레임 내에서 점수의 합은 10점 초과할 수 없음.";

    private final List<Pin> pins;

    public Score() {
        this.pins = new ArrayList<>();
    }

    public void pitch(Pin knockedDownPins) {
        validatePitch(knockedDownPins);
        pins.add(knockedDownPins);
    }

    public void bonus(Pin knockedDownPins) {
        pins.add(knockedDownPins);
    }

    public boolean isUnOpen() {
        return this.pins.isEmpty();
    }

    public boolean isStrikeBonusGame() {
        return this.pins.size() == SECOND_PITCH;
    }

    private void validatePitch(Pin knockedDownPins) {
        if (knockedDownPins.getKnockDownPins() + sumAll() > MAX_SCORE) {
            throw new RuntimeException(OVER_SCORE_IN_EACH_FRAME);
        }
    }

    public int sumAll() {
        return this.pins.stream()
                .mapToInt(Pin::getKnockDownPins)
                .sum();
    }

    public int size() {
        return this.pins.size();
    }

    public List<Integer> getList() {
        return pins.stream()
                .map(Pin::getKnockDownPins)
                .collect(Collectors.collectingAndThen(toList(), Collections::unmodifiableList));
    }
}
