package bowling;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.player.Player;
import bowling.frame.BowlingInfo;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        Frames bowling = Frames.init();
        BowlingInfo bowlingInfo = BowlingInfo.info();

        Player player = Player.of(InputView.inputPlayerName());
        String playerName = player.getName();
        bowlingInfo.put(playerName, bowling);
        ResultView.printGameBoard(playerName, bowlingInfo.getPlayerInfo());

        while (!bowling.isFinish()) {
            int frameNumber = bowling.getFrameNumber();
            Frame frame = bowling.getCurrentFrame();

            frame.pitch(InputView.inputScore(frameNumber));
            bowlingInfo.put(playerName, bowling);
            ResultView.printGameBoard(playerName, bowlingInfo.getPlayerInfo());

            canSecondPitching(bowling, bowlingInfo, playerName, frameNumber, frame);
            Frame nextFrame = frame.next();
            bowling.saveScore(nextFrame);
        }
    }

    private static void canSecondPitching(Frames bowling, BowlingInfo bowlingInfo, String playerName, int frameNumber, Frame frame) {
        while (frame.canPitching()) {
            frame.pitch(InputView.inputScore(frameNumber));
            bowlingInfo.put(playerName, bowling);
            printSecondPitching(bowlingInfo, playerName, frame);
        }
    }

    private static void printSecondPitching(BowlingInfo bowlingInfo, String playerName, Frame frame) {
        if (!frame.isStrikeIgnore()) {
            ResultView.printGameBoard(playerName, bowlingInfo.getPlayerInfo());
        }
    }
}
