package step4.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import step4.view.InputView;

public class PlayersFrames {
    Map<String, Frames> playersFrames;

    public PlayersFrames() {
        this.playersFrames = new HashMap<>();
    }

    public void put(String nameOfPerson, Frames frames) {
        playersFrames.put(nameOfPerson, frames);
    }

    public Frames ofFrames(String player) {
        return playersFrames.get(player);
    }

    public Set<String> playerSet() {
        return playersFrames.keySet();
    }

    public void playGame() {
        for (String player : this.playersFrames.keySet()) {
            Frames frames = playersFrames.get(player);
            Frame frame = frames.ofLast();
            playBowlingUntilFinish(player, frame);
            createNewFrame(frames, frame);
        }
    }

    private void createNewFrame(Frames frames, Frame frame) {
        if (frame.round() != 10 && frame.isFinish()) {
            frame = frame.createFrame(frame.round() + 1);
            frames.add(frame);
        }
    }

    private void playBowlingUntilFinish(String nameOfPerson, Frame frame) {
        while (!frame.isFinish()) {
            int falledPins = InputView.throwBowl(nameOfPerson);
            frame.throwBowl(falledPins);
        }
    }
}
