package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.dto.PlayerDTO;

public class Player {
    private final PlayerName playerName;
    private final Frames frames;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.frames = Frames.init();
    }

    public void bowl(Pins pitch, int frameNo) {
        frames.bowl(pitch, frameNo - 1);
    }

    public String name() {
        return playerName.name();
    }

    public boolean isNthFrameFinished(int round) {
        return nthFrame(round).isFinished();
    }

    public Frame nthFrame(int frameNo) {
        return frames.nthFrame(frameNo - 1);
    }

    public PlayerDTO exportPlayerDTO(){
        return new PlayerDTO(playerName.name(), frames.exportFramesDTO());
    }
}
