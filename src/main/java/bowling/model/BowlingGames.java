package bowling.model;

import bowling.model.frame.FrameNumber;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;
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
                .get();
    }

    private FrameNumber getCurrentFrameNumber() {
        return bowlingGames.stream()
                .map(BowlingGame::getCurrentNumber)
                .min(FrameNumber::compareTo)
                .get();
    }

    public boolean isGameOver() {
        return bowlingGames.stream()
                .map(BowlingGame::isGameOver)
                .allMatch(Predicate.isEqual(TRUE));
    }

    public List<Board> results() {
        return bowlingGames.stream()
                .map(BowlingGame::results)
                .collect(Collectors.toList());
    }
}
