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
            bowling.pitch(InputView.inputScore(frameNumber));
            ResultView.printGameBoard(playerName, bowling);

            canSecondPitching(bowling, playerName);
            bowling.saveScore(bowling.next());
        }
    }

    private static void canSecondPitching(Frames bowling, String playerName) {
        while (bowling.canPitching()) {
            int frameNumber = bowling.getFrameNumber();
            bowling.pitch(InputView.inputScore(frameNumber));
            printSecondPitching(playerName, bowling);
        }
    }

    private static void printSecondPitching(String playerName, Frames bowling) {
        if (!bowling.isStrikeIgnore()) {
            ResultView.printGameBoard(playerName, bowling);
        }
    }
}
