package bowling;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.pitching.Pitching;
import bowling.player.Player;
import bowling.view.InputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BowlingApplication {

    public static void main(String[] args) {
        Map<String, Player> gameInfo = new HashMap<>();
        List<Frame> frameList;
        Frames frames = Frames.startBowling();

        String name = InputView.inputPlayer();
        Player player = new Player(name, frames);
        gameInfo.put(player.getName(), player);

        while (!frames.isFinal()) {
            int frameNumber = frames.getFrameNumber();
            String inputPitchPoint = InputView.inputPitch(frameNumber);

            int pitchingCount = frames.getPitchingCount();
            Pitching pitching = Pitching.pitch(inputPitchPoint, pitchingCount);

            int remainingPins = frames.getRemainingPins();
            frameList = frames.playBowling(frameNumber, pitching, remainingPins);

            frames.pitchingResult(frameList);

            player = new Player(name, frames);
            gameInfo.put(name, player);
        }
    }
}
