package bowling.step2_1.domain.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;

public class Scores {
    private static final int FINAL_FRAME_NUMBER = 10;
    private static final int PINS_CLEAR_POINT = 10;
    private static final int ONE = 1;
    private static final int ZERO = 0;
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final String PITCH_DELIMITER = "|";
    private static final String TWO_PITCH_EXCEPTION = "두번의 투구의 합은 10을 넘길 수 없습니다.";

    private final List<Score> scores;

    public Scores(){
        this.scores = new ArrayList<>();
    }

    private Scores(List<Score> scores) {
        this.scores = scores;
    }

    public void addScore(int pitch, int frameNo) {
        if (scores.size() > ZERO) {
            validatePitch(pitch, frameNo);
        }
        scores.add(Score.of(pitch, findScoreType(pitch)));
    }

    private void validatePitch(int pitch, int frameNo) {
        boolean invalidPitch = isInvalidPitch(pitch);
        if (isNormalFrame(frameNo)) {
            checkInvalidPitch(invalidPitch);
        }

        if (isFinalFrame(frameNo)){
            checkInvalidFinalPitch(invalidPitch);
        }
    }

    private ScoreType findScoreType(int pitch) {
        if (scores.size() > ZERO && getPreScore() + pitch == PINS_CLEAR_POINT){
            return ScoreType.SPARE;
        }

        return Stream.of(ScoreType.values())
                .filter(scoreType -> scoreType.findType(pitch))
                .findFirst()
                .orElse(ScoreType.DEFAULT);
    }

    private int getPreScore() {
        return scores.get(scores.size()-ONE).getScore();
    }

    private boolean isInvalidPitch(int pitch) {
        return getPreScore() + pitch > PINS_CLEAR_POINT;
    }

    private boolean isFinalFrame(int frameNo) {
        return frameNo == FINAL_FRAME_NUMBER;
    }

    private boolean isNormalFrame(int frameNo) {
        return frameNo < FINAL_FRAME_NUMBER;
    }

    private void checkInvalidPitch(boolean invalidPitch) {
        if (invalidPitch) {
            throw new IllegalArgumentException(TWO_PITCH_EXCEPTION);
        }
    }

    private void checkInvalidFinalPitch(boolean invalidPitch) {
        if (!isFirstStrike() && !isSecondStrikeOrSpare()){
            checkInvalidPitch(invalidPitch);
        }
    }

    private boolean isFirstStrike() {
        return scores.size() == ONE && getfirstScore() == PINS_CLEAR_POINT;
    }

    private int getfirstScore() {
        return scores.get(INDEX_ZERO).getScore();
    }

    private boolean isSecondStrikeOrSpare() {
        if (scores.size() > ONE){
            Scores subScores = scores.subList(0,2).stream()
                                                  .collect(collectingAndThen(Collectors.toList(),Scores::new));
            return getSecondScore() == PINS_CLEAR_POINT || subScores.pitchSum() == PINS_CLEAR_POINT;
        }
        return false;
    }

    private int getSecondScore() {
        return scores.get(INDEX_ONE).getScore();
    }

    public List<Score> getScores() {
        return Collections.unmodifiableList(scores);
    }

    public int size() {
        return scores.size();
    }

    public boolean hasStrikeorSpare() {
        return pitchSum() == PINS_CLEAR_POINT;
    }

    public int pitchSum() {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    @Override
    public String toString() {
        return scores.stream()
                     .map(score -> score.getDisplay())
                     .collect(Collectors.joining(PITCH_DELIMITER));
    }
}
