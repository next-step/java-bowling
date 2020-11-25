package bowling.domain.member;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.List;

public class Member {

    private final MemberName memberName;
    private final Frames frames;

    public Member(MemberName memberName, Frames frames) {
        this.memberName = memberName;
        this.frames = frames;
    }

    public static Member of(String name) {
        return new Member(MemberName.of(name), Frames.create());
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
