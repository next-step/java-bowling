package bowling.domain.bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.member.MemberName;
import bowling.domain.pin.Pin;

import java.util.List;

public class Bowling {

    private final MemberName memberName;
    private final Frames frames;

    private Bowling(MemberName memberName, Frames frames) {
        this.memberName = memberName;
        this.frames = frames;
    }

    public static Bowling of(String name) {
        return new Bowling(MemberName.of(name), Frames.create());
    }

    public static Bowling of(String name, List<Frame> frames) {
        return new Bowling(MemberName.of(name), Frames.of(frames));
    }

    public String getName() {
        return memberName.getName();
    }

    public boolean isFinished() {
        return frames.isFinished();
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFrameNumber();
    }

    public boolean throwBall(Pin pin) {
        return frames.record(pin);
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public List<Integer> calculateScores() {
        return frames.calculateScores();
    }
}
