package bowling.step2.domain.pitch;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pitches {
    private static final int FIRST_TRY = 0;
    private static final int SECOND_TRY = 1;
    private static final int STRIKE = 10;

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
        return pitches.get(pitches.size() - 1);
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
            throw new IllegalArgumentException("일반 투구 2번의 합계는 10을 넘을 수 없습니다.");
        }
    }

    private boolean isFramePitchLimit() {
        return pitches.size() == 2 && pitchSum() > 10;
    }

    public void validateFinalPitches() {
        if (!isFirstPitchStrike() && isFramePitchLimit()){
            throw new IllegalArgumentException("일반 투구 2번의 합계는 10을 넘을 수 없습니다.");
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
        return pitchSum() == 10 || pitches.size() == 2;
    }

    public boolean finalEnd() {
        if (pitches.size() == 2 && pitchSum() < 10){
            return true;
        }
        if (pitches.size() == 3){
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
                      .collect(Collectors.joining("|"));
    }
}
