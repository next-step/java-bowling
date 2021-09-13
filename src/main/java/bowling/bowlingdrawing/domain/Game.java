package bowling.bowlingdrawing.domain;

public class Game {
    private final Frames frames = new Frames();
    private final Pitchings pitchings = new Pitchings();

    public void pitch(int pins) {
        Pitching nextPitching = pitchings.nextPitching(pins);

        if(!frames.fullFrame()){
            frames.pitch(nextPitching);
        }
    }

    public Frames frames() {
        return frames;
    }

    public Pitchings pitchings() {
        return pitchings;
    }

    public boolean end() {
        return frames.end();
    }

    public int currentFrame() {
        return frames.currentFrame();
    }
}
