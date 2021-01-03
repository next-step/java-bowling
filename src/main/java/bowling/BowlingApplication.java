package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.ui.InputView;
import bowling.ui.ResultView;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.Frames.FIRST_FRAME_NO;
import static bowling.domain.Frames.LAST_FRAME_NO;

public class BowlingApplication {
    public static void main(String[] args) {
        int numberOfPeople = InputView.askNumberOfPeople();
        BowlingGame bowlingGame = createBowlingGame(numberOfPeople);

        ResultView.printInitialization(bowlingGame);

        for(int i = FIRST_FRAME_NO; i <= LAST_FRAME_NO; i++) {
            playBowling(bowlingGame, i);
        }
    }

    private static BowlingGame createBowlingGame(int numberOfPeople) {
        List<Player> players = new ArrayList<>();

        for(int i = 0; i < numberOfPeople; i++) {
            players.add(new Player(InputView.askPlayerName()));
        }

        return new BowlingGame(players);
    }

    private static void playBowling(BowlingGame bowlingGame, int frameNo) {
        bowlingGame.forEachPlayer(player -> repeatBowlingAndPrint(frameNo, player, bowlingGame));
    }

    private static void repeatBowlingAndPrint(int frameNo, Player player, BowlingGame bowlingGame) {
        while(!player.isNthFrameOver(frameNo)) {
            player.bowl(InputView.askNumberOfPins(player.getName()));
            ResultView.print(bowlingGame);
        }
    }
}