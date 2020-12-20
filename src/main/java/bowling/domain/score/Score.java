package bowling.domain.score;

import bowling.state.BowlingState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By mand2 on 2020-12-21.
 */
public class Score {

    public final static int MAX_SCORE = 10;
    public final static int FIRST_PITCH = 1;
    public final static int SECOND_PITCH = 2;

    public static final String OVER_SCORE_IN_EACH_FRAME = "한 프레임 내에서 점수의 합은 10점 초과할 수 없음.";

    private final List<Pitch> pitches;

    public Score() {
        this.pitches = new ArrayList<>();
    }

    public void pitch(Pitch knockedDownPins) {
        validatePitch(knockedDownPins);
        pitches.add(knockedDownPins);
    }

    public void bonus(Pitch knockedDownPins) {
        pitches.add(knockedDownPins);
    }

    public boolean isStrike() {
        return this.pitches.size() == FIRST_PITCH && sum() == MAX_SCORE;
    }

    public boolean isOpen() {
        return this.pitches.size() == FIRST_PITCH && sum() < MAX_SCORE;
    }


    public boolean isSpare() {
        return this.pitches.size() == SECOND_PITCH && sum() == MAX_SCORE;
    }

    public boolean isMiss() {
        return this.pitches.size() == SECOND_PITCH && sum() < MAX_SCORE;
    }

    private void validatePitch(Pitch knockedDownPins) {
        if (knockedDownPins.getScore() + sum() > MAX_SCORE) {
            throw new RuntimeException(OVER_SCORE_IN_EACH_FRAME);
        }
    }

    private int sum() {
        return this.pitches.stream().mapToInt(Pitch::getScore).sum();
    }


}
