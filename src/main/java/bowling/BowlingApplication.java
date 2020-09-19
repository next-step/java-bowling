package bowling;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.frame.NormalFrame;
import bowling.player.Player;
import bowling.player.PlayerInfo;
import bowling.view.InputView;

public class BowlingApplication {

    public static void main(String[] args) {
        Frames frames = Frames.start();
        Frame frame = NormalFrame.first();
        Frames currentFrames = Frames.of(frames, frame);
        PlayerInfo playerInfo = PlayerInfo.info();

        String name = InputView.inputPlayerName();
        Player player = Player.join(name, frame);
        playerInfo.put(name, player);

        while (!currentFrames.isNormalFinish()) {
            int frameNumber = currentFrames.getFrameNumber();
            frame = BowlingAdministrator.playBowling(InputView.inputScore(frameNumber), frame);

            frame = BowlingAdministrator.normalFramePitchi(frame, frameNumber);
            playerInfo.put(name, player);
            frame = frame.next();
            currentFrames = Frames.of(frames, frame);
        }

        while (frame.canFinalPitching()) {
            frame = BowlingAdministrator.playBowling(InputView.inputScore(frame.getFrameNumber()), frame);
            playerInfo.put(name, player);
        }

        System.out.println(playerInfo);
    }
}
