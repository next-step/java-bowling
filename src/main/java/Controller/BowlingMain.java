package Controller;

import View.InView;
import View.OutView;
import domain.BowlingGame;
import domain.Player;

import java.util.stream.IntStream;

import static domain.BowlingGame.MAX_BOWL_COUNT;

public class BowlingMain {
    public static void main(String[] args) {
        Player player = new Player(requestPlayerName());
        BowlingGame bowlingGame = new BowlingGame();
        playBowling(bowlingGame, player);
    }

    private static String requestPlayerName() {
        OutView.askPlayerName();
        return InView.getString();
    }

    private static int requestFrameScore(int frameNumber) {
        OutView.askFrameScore(frameNumber);
        return InView.getInt();
    }

    private static void playBowling(BowlingGame bowlingGame, Player player) {
        IntStream.rangeClosed(1, MAX_BOWL_COUNT)
                .filter(ballCount -> !bowlingGame.isGameOver())
                .map(ballCount -> requestFrameScore(bowlingGame.getNextFrameNumber()))
                .peek(score -> bowlingGame.playBowling(score))
                .peek(score -> OutView.showFrameHeader())
                .peek(score -> OutView.showFrameResult(player.getName(), bowlingGame.getResult()))
                .peek(frame -> OutView.showFrameResult("", bowlingGame.getFormattedPointResult()))
                .forEach(frame -> OutView.printBlankLine());
    }
}
