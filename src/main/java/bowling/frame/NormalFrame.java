package bowling.frame;

import bowling.pitching.Pitching;
import bowling.pitching.PitchingResult;

public class NormalFrame extends Frame {

    private NormalFrame(int number, PitchingResult pitchingResult) {
        super(number, pitchingResult);
    }

    public NormalFrame(int number) {
        super(number);
    }

    public static NormalFrame pitchi(int number, Pitching pitching, int remainingPins) { // 1개의 프레임에 첫 번째 투구 결과를 저장한다.
        PitchingResult pitchingResult = PitchingResult.from(pitching, remainingPins, 2); // 첫번째 투구 결과를 받아와야한다.
        return new NormalFrame(number, pitchingResult);
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "OrdinayFrame{" +
                "number=" + number +
                ", pitchingResults=" + pitchingResults +
                '}';
    }
}
