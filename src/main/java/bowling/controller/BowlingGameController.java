package bowling.controller;

import bowling.domain.Player;
import bowling.domain.frame.BowlingFrames;
import bowling.domain.pin.FallenPins;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {

    public static void main(String[] args) {
        play();
    }

    private static void play() {
        Player player = new Player(InputView.getPlayerName());

        for (int i = 0; i < BowlingFrames.LAST_FRAME; i++) {
            playFrame(player, i);
        }

    }

    private static void playFrame(Player player, int indexOfFrame) {
        while (!player.isFinishedBowling(indexOfFrame)) {
            playTurn(player, indexOfFrame);
            ResultView.printFrame(player);
        }
    }

    private static void playTurn(Player player, int indexOfFrame) {
        try {
            FallenPins fallenPins = InputView.getFallenPins(indexOfFrame + 1);
            player.bowlBall(indexOfFrame, fallenPins);
        } catch(Exception e) {
            ResultView.printError(e);
        }
    }

}
