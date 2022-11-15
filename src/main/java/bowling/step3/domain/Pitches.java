package bowling.step3.domain;

import java.util.ArrayList;
import java.util.List;

public class Pitches {

    private static final String PIN_COUNT_EXCEPTION = "핀의 개수는 합10을 넘을 수 없습니다.";

    private final List<Pitch> pitches;

    public Pitches() {
        this.pitches = new ArrayList<>();
    }

    public void add(int count) {
        if(sum()+count > 10){
            throw new IllegalArgumentException(PIN_COUNT_EXCEPTION);
        }
        this.pitches.add(new Pitch(count));
    }

    public int sum() {
        return this.pitches.stream().mapToInt(Pitch::count).sum();
    }

    public int first() {
        return this.pitches.get(0).count();
    }

    public int second() {
        return this.pitches.get(1).count();
    }

    public Boolean hasStrike() {
        return this.pitches.stream().anyMatch(Pitch::isStrike);
    }

    public boolean hasSpare() {
        if (this.pitches.size() < 2) {
            return false;
        }
        int lastPitch = this.pitches.size() - 1;
        return this.pitches.get(lastPitch).count() + pitches.get(lastPitch - 1).count() == 10;
    }

    public int getSize() {
        return pitches.size();
    }

    public List<Pitch> value() {
        return new ArrayList<>(pitches);
    }
}
