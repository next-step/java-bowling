package Controller;

import View.InView;
import View.OutView;
import domain.Bowling;
import domain.FrameCounter;
import domain.Player;

import java.util.stream.IntStream;

import static domain.Bowling.MAX_BOWL_COUNT;
import static domain.Frame.ZERO;

public class BowlingMain {
    public static void main(String[] args) {
        Player player = new Player(requestPlayerName());
        Bowling bowling = new Bowling();
        playBowling(bowling, player);
    }

    private static String requestPlayerName() {
        OutView.askPlayerName();
        return InView.getString();
    }

    private static int requestFramePoint(int frameNumber) {
        OutView.askFrameScore(frameNumber);
        return InView.getInt();
    }

    private static void playBowling(Bowling bowling, Player player) {
        IntStream.range(ZERO, MAX_BOWL_COUNT)
                .filter(count -> !bowling.isBowlingEnd())
                .map(count -> requestFramePoint(FrameCounter.getFrameCounter()))
                .peek(bowling::playBowling)
                .peek(point -> OutView.showFrameHeader())
                .peek(point -> OutView.showFrameResult(player.getName(), bowling.getFormattedPointResult()))
                .peek(point -> OutView.showFrameResult("", bowling.getFormattedScoreResult()))
                .peek(point -> OutView.printBlankLine())
                .forEach(point -> {
                    if (!bowling.isBowlingEnd()) {
                        bowling.getNextFrame();
                    }
                });
    }
}
