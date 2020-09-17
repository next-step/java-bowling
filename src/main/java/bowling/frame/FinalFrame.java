package bowling.frame;

import bowling.pitching.Pitching;
import bowling.pitching.PitchingResult;

public class FinalFrame extends Frame {

    private int bonusPitchingCount;

    private FinalFrame(PitchingResult pitchingResult) {
        super(10, pitchingResult);
    }

    private FinalFrame() {
        super(10);
    }

    public static FinalFrame pitchi(Pitching pitching, int remainingPins, int bonusPitchingCount) {
        PitchingResult pitchingResult = PitchingResult.from(pitching, remainingPins, bonusPitchingCount);
//        pitchingResult =
        return new FinalFrame(pitchingResult);
    }

    public static FinalFrame from() {
        return new FinalFrame();
    }

    public PitchingResult finalPitching(PitchingResult pitchingResult) {
        PitchingResult prevPitchingResult = getPrevPitchingResult();
        if (pitchingResult.getFirstPitchingCount() && pitchingResult.getPitchResultState().equals("X")) {

        }

        if (pitchingResult.getPitchingCount() == 2 && pitchingResult.getPitchResultState().equals("X") && prevPitchingResult.equals("X")) {

        }

        if (pitchingResult.getPitchingCount() == 2 && pitchingResult.getPitchResultState().equals("/")) {

        }
        return pitchingResult;
    }



    @Override
    public boolean isFinal() {

        return false;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
