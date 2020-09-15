package bowling;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.pitching.Pin;
import bowling.pitching.Pins;
import bowling.player.Player;
import bowling.pitching.PitchingState;
import bowling.view.ResultView;
import bowling.view.InputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bowling.global.utils.CommonConstant.GAME_FINISH_COUNT;

public class BowlingApplication {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayer());
        Map<Integer, Pins> score = new HashMap<>();

        while (Frames.CURRENT_FRAME_NUMBER < GAME_FINISH_COUNT) {
            List<Pin> pinList = new ArrayList<>();
            List<Frame> frameList = new ArrayList<>();

            for (int i = 1; i <= Frames.PITCH_COUNT; i++) {
                ResultView.printFrameViewHeader(GAME_FINISH_COUNT, player.getName(), score);
                String inputPitchPoint = InputView.inputPitch(Frames.CURRENT_FRAME_NUMBER);
                Frames frames = BowlingGame.playBowling(pinList, frameList, inputPitchPoint, i);
                if (pinList.get(0).getState().equals(PitchingState.STRIKE.toString())) {
                    BowlingGame.playBowling(pinList, frameList, "0", 2);
                    break;
                }
            }
        }
    }
}
