package bowling;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.player.Player;
import bowling.player.PlayerInfo;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        Frames frames = Frames.init();
        PlayerInfo playerInfo = PlayerInfo.info();

        Player player = Player.of(InputView.inputPlayerName(), frames);
        String playerName = player.getName();
        playerInfo.put(playerName, player);
        ResultView.printGameBoard(playerName, playerInfo.getPlayerInfo());

        while (!frames.isFinish()) {
            int frameNumber = frames.getFrameNumber();
            Frame frame = frames.getCurrentFrame();

            frame.pitch(InputView.inputScore(frameNumber));
            playerInfo.put(playerName, Player.of(playerName, frames));
            ResultView.printGameBoard(playerName, playerInfo.getPlayerInfo());

            canSecondPitching(frames, playerInfo, playerName, frameNumber, frame);
            Frame nextFrame = frame.next();
            frames.saveScore(nextFrame);
        }
    }

    private static void canSecondPitching(Frames frames, PlayerInfo playerInfo, String playerName, int frameNumber, Frame frame) {
        while (frame.canPitching()) {
            frame.pitch(InputView.inputScore(frameNumber));
            playerInfo.put(playerName, Player.of(playerName, frames));
            printSecondPitching(playerInfo, playerName, frame);
        }
    }

    private static void printSecondPitching(PlayerInfo playerInfo, String playerName, Frame frame) {
        if (!frame.isStrikeIgnore()) {
            ResultView.printGameBoard(playerName, playerInfo.getPlayerInfo());
        }
    }
}
