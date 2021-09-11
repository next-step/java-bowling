package bowling;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Pins;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

import static bowling.view.ResultView.printBowlingGame;

public class BowlingApplication {
    public static void main(String args[]) {

        int count = InputView.getPlayerCount();
        List<String> players = InputView.getPlayerNames(count);

        List<Frames> frames = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            frames.add(Frames.newInstance());
            Frame currentFrame = frames.get(i).currentFrame();
        }

        while(frames.get(1).size() != 10) {
            for (int i = 0; i < players.size(); i++) {
                Frame currentFrame = frames.get(i).currentFrame().next();
                if (!currentFrame.isFinish()) {
                    currentFrame.bowl(Pins.of(InputView.getFrameScore(players.get(i))));
                    frames.get(i).add(currentFrame);
                    ResultView.printTitle();
                    ResultView.printBowlingGame(players, frames);
                }
            }
        }

    }
//        Frames frames = Frames.newInstance();
//        Frame frame = frames.currentFrame();
//
//        while (!frame.isFinish()) {
//            frame.bowl(Pins.of(InputView.getFrameScore(frame.getFrameNumber())));
//            frames.add(frame);
//            ResultView.printFrames(players.get(i), frames);
//            frame = frame.next();
//        }
//    }
}
