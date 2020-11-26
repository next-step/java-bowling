package bowling.domain.bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.member.MemberName;

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

    public String getName() {
        return memberName.getName();
    }

    public boolean isFinished() {
        return frames.isFinished();
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFrameNumber();
    }

    public void throwBall(int score) {
        frames.record(score);
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }
}
