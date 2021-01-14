package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;


public class BowlingGameController {

    public static void run() {

        int playerNumber = askPlayerNumber();
        String[] playerNames = askPlayerNames(playerNumber);

        //String playerName = getPlayerName();

        Player player = Player.from("Test");

        Players players = Players.from(playerNames);

        Frames frames = Frames.of();

        BowlingGame bowlingGame = BowlingGame.of(player, frames);

        printEmptyFrames(bowlingGame.getPlayer(), bowlingGame.getFrames().MAX_FRAME_COUNT);

        while (!bowlingGame.isEnd()) {

            int knockedDownPins = inputKnockedDownPins(frames.getLast().getIndex());

            frames.execute(knockedDownPins);

            printCurrentFrame(bowlingGame, bowlingGame.getFrames().MAX_FRAME_COUNT);

            frames.makeNextFrames();

        }
    }

    private static String[] askPlayerNames(int playerNumber) {
        return InputView.inputPlayerNames(playerNumber);
    }

    private static int askPlayerNumber() {
        return InputView.inputPlayerNumber();
    }

    private static int inputKnockedDownPins(int frameIndex) {
        return InputView.inputKnockedDownPins(frameIndex);
    }

    private static void printCurrentFrame(BowlingGame bowlingGame, int maxFrameCount) {
        ResultView.printCurrentFrame(bowlingGame, maxFrameCount);
    }

    private static void printEmptyFrames(Player player, int maxFrameCount) {
        ResultView.printEmptyFrames(player, maxFrameCount);
    }

}
