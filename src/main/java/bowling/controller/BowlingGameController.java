package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;


public class BowlingGameController {


    public static void run() {


        String playerName = getPlayerName();

        Player player = Player.from(playerName);

        Frames frames = Frames.of(new RandomPitchStrategy());


        BowlingGame bowlingGame = BowlingGame.of(player, frames);

        printEmptyFrames(bowlingGame.getPlayer(), bowlingGame.getFrames().MAX_FRAME_COUNT);

        while(!bowlingGame.isEnd()){
            frames.execute();
            printCurrentFrame(bowlingGame, bowlingGame.getFrames().MAX_FRAME_COUNT);

            frames.makeNextFrames();

        }
    }

    private static void printCurrentFrame(BowlingGame bowlingGame, int maxFrameCount) {
        ResultView.printCurrentFrame(bowlingGame, maxFrameCount);
    }

    private static void printEmptyFrames(Player player, int maxFrameCount) {
        ResultView.printEmptyFrames(player, maxFrameCount);
    }

    private static String getPlayerName() {
        return InputView.inputPlayerName();
    }
}
