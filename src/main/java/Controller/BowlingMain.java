package Controller;

import View.InView;
import View.OutView;
import domain.BowlingGame;
import domain.Player;

import java.util.stream.IntStream;

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
        IntStream.rangeClosed(1, BowlingGame.MAX_BOWL_COUNT)
                .filter(n -> !bowlingGame.isGameOver())
                .map(frame -> requestFrameScore(bowlingGame.getNextFrameNumber()))
                .peek(n -> OutView.showFrameHeader())
                .peek(score -> bowlingGame.playBowling(score))
                .forEach(result -> OutView.showFrameResult(player.getName(), bowlingGame.getResult()));
    }
}
