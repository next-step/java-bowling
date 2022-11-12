package bowling.domain;

public class Bowling {
    private User user;

    private Frames frames;

    public Bowling(User user) {
        this.user = user;
        this.frames = new Frames();
    }

    public String userName() {
        return user.name();
    }

    public int pitching(FrameNumber frame, BowlingStrategy bowlingStrategy) {
        return frames.pitch(frame, bowlingStrategy);
    }

    public boolean availablePitch(FrameNumber frame) {
        return frames.availablePitching(frame);
    }

    public String score(FrameNumber frame) {
        return frames.score(frame);
    }
}
