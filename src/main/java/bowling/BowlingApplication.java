package bowling;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        Frames bowling = Frames.init();

        Player player = Player.of(InputView.inputPlayerName());
        String playerName = player.getName();
        ResultView.printGameBoard(playerName, bowling);

        while (!bowling.isFinish()) {
            int frameNumber = bowling.getFrameNumber();
            Frame frame = bowling.getCurrentFrame();

            frame.pitch(InputView.inputScore(frameNumber));
            ResultView.printGameBoard(playerName, bowling);

            canSecondPitching(bowling, playerName, frame);
            Frame nextFrame = frame.next();
            bowling.saveScore(nextFrame);
        }
    }

    private static void canSecondPitching(Frames bowling, String playerName, Frame frame) {
        while (frame.canPitching()) {
            int frameNumber = bowling.getFrameNumber();
            frame.pitch(InputView.inputScore(frameNumber));
            printSecondPitching(playerName, frame, bowling);
        }
    }

    private static void printSecondPitching(String playerName, Frame frame, Frames bowling) {
        if (!frame.isStrikeIgnore()) {
            ResultView.printGameBoard(playerName, bowling);
        }
    }
}
