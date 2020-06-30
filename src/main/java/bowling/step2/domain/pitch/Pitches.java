package bowling.step2.domain.pitch;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pitches {
    private static final int FIRST_TRY = 0;
    private static final int NORMAL_PITCHES_END = 2;
    private static final int BONUS_PITCHES_END = 3;
    private static final int PINS_COUNT = 10;
    private static final int STRIKE = 10;
    private static final int ONE = 1;
    private static final String PITCHES_EXCEPTION = "일반 투구 2번의 합계는 10을 넘을 수 없습니다.";
    private static final String PITCH_DELIMITER = "|";

    private final List<Pitch> pitches;

    public Pitches() {
        this.pitches = new ArrayList<>();
    }

    public Pitches addPitch(int pitch) {
        pitches.add(decideNormalPitchType(pitch));
        return this;
    }

    private Pitch decideNormalPitchType(int pitch) {
        if (CollectionUtils.isEmpty(pitches)){
            return new NormalPitch(pitch, PitchType.DEFAULT).firstPitch();
        }
        return getLastPitch().nextPitch(pitch);
    }

    private Pitch getLastPitch() {
        return pitches.get(pitches.size() - ONE);
    }

    public Pitches addFinalPitch(int pitch) {
        pitches.add(decideFinalPitchType(pitch));
        return this;
    }

    private Pitch decideFinalPitchType(int pitch) {
        if (CollectionUtils.isEmpty(pitches)){
            return new NormalPitch(pitch, PitchType.DEFAULT).firstPitch();
        }
        if (getLastPitch().pitchType.equals(PitchType.STRIKE) || pitch == STRIKE){
            return new NormalPitch(pitch, PitchType.DEFAULT).firstPitch();
        }
        return getLastPitch().nextPitch(pitch);
    }

    public List<Pitch> getPitches() {
        return pitches;
    }

    public void validateNormalPitches() {
        if (isFramePitchLimit()){
            throw new IllegalArgumentException(PITCHES_EXCEPTION);
        }
    }

    private boolean isFramePitchLimit() {
        return pitches.size() == NORMAL_PITCHES_END && pitchSum() > PINS_COUNT;
    }

    public void validateFinalPitches() {
        if (!isFirstPitchStrike() && isFramePitchLimit()){
            throw new IllegalArgumentException(PITCHES_EXCEPTION);
        }
    }

    private boolean isFirstPitchStrike() {
        return getFirstPitch().equals(PitchType.STRIKE);
    }

    private PitchType getFirstPitch() {
        return pitches.get(FIRST_TRY).pitchType;
    }

    private int pitchSum(){
        return pitches.stream()
                      .mapToInt(pitch -> pitch.pitch)
                      .sum();
    }

    public boolean normalEnd() {
        return pitchSum() == PINS_COUNT || pitches.size() == NORMAL_PITCHES_END;
    }

    public boolean finalEnd() {
        if (pitches.size() == NORMAL_PITCHES_END && pitchSum() < PINS_COUNT){
            return true;
        }
        if (pitches.size() == BONUS_PITCHES_END){
            return true;
        }
        return false;
    }

    public int size(){
        return pitches.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitches pitches1 = (Pitches) o;
        return Objects.equals(pitches, pitches1.pitches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitches);
    }

    @Override
    public String toString() {
        return pitches.stream()
                      .map(pitch -> pitch.pitchType.convertToDisplay(pitch.pitch))
                      .collect(Collectors.joining(PITCH_DELIMITER));
    }
}
