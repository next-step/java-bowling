package bowling;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.frame.NormalFrame;
import bowling.player.Player;
import bowling.player.PlayerInfo;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.LinkedList;

public class BowlingApplication {

    public static void main(String[] args) {
        Frame frame = NormalFrame.first();
        LinkedList<Frame> nowFrame = new LinkedList<>();
        Frames frames = Frames.saveScore(nowFrame, frame);
        PlayerInfo playerInfo = PlayerInfo.info();

        String name = InputView.inputPlayerName();
        Player player = Player.of(name, frames);
        playerInfo.put(name, player);
        ResultView.printGameBoard(name, playerInfo.getPlayerInfo());

        while (!frames.isNormalFinish()) {
            frame = BowlingAdministrator.playBowling(frame, InputView.inputScore(frames.getFrameNumber()));
            playerInfo.put(name, Player.of(name, frames));
            ResultView.printGameBoard(name, playerInfo.getPlayerInfo());

            frame = normalFramePitchi(frame, frames.getFrameNumber());
            playerInfo.put(name, Player.of(name, frames));
            if (!frame.isStrikeIgnore()) {
                ResultView.printGameBoard(name, playerInfo.getPlayerInfo());
            }

            frame = frame.next();
            frames = Frames.saveScore(nowFrame, frame);
        }

        while (frame.canFinalPitching()) {
            frame = BowlingAdministrator.playBowling(frame, InputView.inputScore(frame.getFrameNumber()));
            playerInfo.put(name, Player.of(name, frames));
            ResultView.printGameBoard(name, playerInfo.getPlayerInfo());
        }
    }

    public static Frame normalFramePitchi(Frame frame, int frameNumber) {
        if (frame.canNormalPitching()) {
            frame = BowlingAdministrator.playBowling(frame, InputView.inputScore(frameNumber));
        }
        return frame;
    }
}
