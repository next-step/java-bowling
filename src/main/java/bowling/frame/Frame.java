package bowling.frame;

import bowling.pitching.PitchingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Frame {

    protected int number;
    protected List<PitchingResult> pitchingResults;

    public Frame(int number, PitchingResult pitchingResult) { // 프레임 번호와 Pitching 결과를 저장
        this.number = number;
        this.pitchingResults = new ArrayList<>();
        this.pitchingResults.add(pitchingResult);
    }

    public Frame(int number) {
        this.number = number;
    }

    protected PitchingResult getPrevPitchingResult() {
        int last = pitchingResults.size() - 1;
        return pitchingResults.get(last);
    }

    public Frame nextFrame() {
        return new NormalFrame(getNumber() + 1);
    }

    public abstract boolean isFinal();

    public abstract int getNumber();

    public List<PitchingResult> getPitchingResults() {
        return pitchingResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return number == frame.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "number=" + number +
                ", pitchingResults=" + pitchingResults +
                '}';
    }
}
