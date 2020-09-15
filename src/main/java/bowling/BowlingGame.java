package bowling;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.pitching.Pin;
import bowling.pitching.Pins;

import java.util.List;

public class BowlingGame {

    public static Frames playBowling(List<Pin> pinList, List<Frame> frameList, String pitchPoint, int pitchCount) {
        Pins pins = Pins.eachPitchResult(pinList, pitchPoint, pitchCount);
        return Frames.nextFrame(frameList, pins);
    }
}
