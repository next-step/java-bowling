package bowling.model;

import bowling.model.frame.FrameNumber;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class BowlingGames {

    private List<BowlingGame> bowlingGames;

    private BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = bowlingGames;
    }

    public static BowlingGames settingOf(List<Player> players) {
        List<BowlingGame> bowlingGames = players.stream()
                .map(BowlingGame::settingOf)
                .collect(toList());
        return new BowlingGames(bowlingGames);
    }

    public BowlingGame getCurrentGame() {
        FrameNumber currentNumber = getCurrentFrameNumber();

        return bowlingGames.stream()
                .filter(bowlingGame -> bowlingGame.isYourTurn(currentNumber))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    private FrameNumber getCurrentFrameNumber() {
        return bowlingGames.stream()
                .map(BowlingGame::getCurrentNumber)
                .min(FrameNumber::compareTo)
                .orElseThrow(NoSuchElementException::new);
    }

    public boolean isGameOver() {
        return bowlingGames.stream()
                .allMatch(BowlingGame::isGameOver);
    }

    public List<Board> results() {
        return bowlingGames.stream()
                .map(BowlingGame::results)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }
}
