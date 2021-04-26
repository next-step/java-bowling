package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.dto.PlayerDTO;

public class Player {
    private final Name name;
    private final Frames frames;

    public Player(String name) {
        this.name = new Name(name);
        this.frames = Frames.init();
    }

    public void bowl(int value, int frameNo) {
        frames.bowl(value, frameNo - 1);
    }

    public String name() {
        return name.name();
    }

    public boolean isNthFrameFinished(int round) {
        return nthFrame(round).isFinished();
    }

    public Frame nthFrame(int frameNo) {
        return frames.nthFrame(frameNo - 1);
    }

    public PlayerDTO exportPlayerDTO(){
        return new PlayerDTO(name.name(), frames.exportFramesDTO());
    }
}
