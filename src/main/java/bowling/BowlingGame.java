package bowling;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.referee.Referee;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {

    public static final int MAX_FRAME = 10;
    public static final int START_FRAME = 0;

    public static void main(String[] args) {
        Player person = InputView.user();
        Referee referee = Referee.create();
        referee.readyPerson(person);

        runBowlingGames(referee);
    }

    private static void runBowlingGames(Referee referee) {
        int currentFrame = START_FRAME;
        do {
            ResultView.showCurrentFrameInfo(currentFrame);
            runPlayer(referee);

            if (referee.isEndFrame(currentFrame)) {
                currentFrame++;
            }
        } while (currentFrame < MAX_FRAME);
    }

    private static void runPlayer(Referee referee) {
        for (Player player : referee.persons()) {
            Frames frames = referee.framesOfPerson(player);
            Frame frame = frames.latestFrame();

            next(referee, player, frame);

            ResultView.showPersonNameOnBoard(player);
            ResultView.showPlayerFrame(player, referee);
            ResultView.showPlayerScore(player, referee);
        }
    }

    private static void next(Referee referee, Player player, Frame frame) {
        if (frame.hasNextRound()) {
            int downPinsCount = InputView.downPinsCount();
            ResultView.showHead();
            frame.roll(downPinsCount);
            frame.nextRound().ifPresent(frame1 -> referee.write(player, frame1));
        }
    }

}
