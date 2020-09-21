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

        String name = InputView.inputPlayerName();
        Player player = Player.of(name, frames);
        playerInfo.put(name, player);
        ResultView.printGameBoard(name, playerInfo.getPlayerInfo());

        while (!frames.isFinish()) {
            // 1회차 투구
            Frame frame = frames.getFrames().get(frames.getCurrentFrame());
            frame.pitch(InputView.inputScore(frames.getFrameNumber()));
            playerInfo.put(name, Player.of(name, frames));
            ResultView.printGameBoard(name, playerInfo.getPlayerInfo());

            // 아래 리팩토링
            if (frame.canPitching()) {
                frame.pitch(InputView.inputScore(frame.getFrameNumber()));
            }
            playerInfo.put(name, Player.of(name, frames));
            if (!frame.isStrikeIgnore()) {
                ResultView.printGameBoard(name, playerInfo.getPlayerInfo());
            }

            frame = frame.next();
            frames.saveScore(frame);
        }

//        while (frame.canFinalPitching()) {
//            frame = playBowling(frame, InputView.inputScore(frame.getFrameNumber()));
//            playerInfo.put(name, Player.of(name, frames));
//            ResultView.printGameBoard(name, playerInfo.getPlayerInfo());
//        }
    }

}
