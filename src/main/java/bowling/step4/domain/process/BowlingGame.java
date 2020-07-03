package bowling.step4.domain.process;

import bowling.step4.domain.frame.Frame;
import bowling.step4.domain.frame.Frames;
import bowling.step4.domain.player.Player;

import java.util.List;

public class BowlingGame {

    private final Player player;
    private final Frames frames;

    private BowlingGame(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static BowlingGame of(String inputName) {
        return new BowlingGame(Player.of(inputName), new Frames());
    }

    public boolean isGameOver() {
        return frames.isGameEnd();
    }

    public BowlingGame play(int pitch){
        frames.progress(pitch);
        return this;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getFrameNo() {
        return frames.getLastFrame().getframeNo();
    }

    public String viewFramesDisplay() {
        return frames.toString();
    }

    public List<Integer> getFramesScores() {
        return frames.calculateScores();
    }

    public Frames getFrames() {
        return frames;
    }

    public Frame getLastFrame(){
        return getFrames().getLastFrame();
    }

    public boolean isOneFrameOver(Frame lastFrame) {
        return frames.isOneFrameEnd(lastFrame) || isGameOver();
    }
}
