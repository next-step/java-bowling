package bowling.domain.game;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Round;
import bowling.domain.result.GameResults;
import bowling.domain.user.User;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class BowlingGames {

    private List<BowlingGame> values;
    private Round round;

    private BowlingGames(List<BowlingGame> values, Round round) {
        this.values = values;
        this.round = round;
    }

    public static BowlingGames fromWithUserNames(List<String> userNames) {
        return userNames.stream()
                .map(User::from)
                .map(BowlingGame::readyGame)
                .collect(collectingAndThen(toList(), BowlingGames::from));
    }

    public static BowlingGames from(List<BowlingGame> bowlingGames) {
        return of(bowlingGames, Round.FIRST);
    }

    public static BowlingGames of(List<BowlingGame> bowlingGames, Round round) {
        return new BowlingGames(bowlingGames, round);
    }

    public String nowTurnUserName() {
        return nowTurnBowlingGame().userName();
    }
    
    public void bowlNowTurn(Pin pin) {
        nowTurnBowlingGame().bowl(pin);
        nextRound();
    }

    private void nextRound() {
        boolean isNext = values.stream()
                .map(BowlingGame::round)
                .noneMatch(round1 -> round1.equals(round));

        if(isNext) {
            this.round = round.nextRound();
        }
    }

    public boolean isGameEnd() {
        return values.stream()
                .allMatch(bowlingGame -> bowlingGame.isGameEnd());
    }

    public GameResults createResults() {
        return values.stream()
                .map(BowlingGame::createResult)
                .collect(collectingAndThen(toList(), GameResults::new));
    }

    private BowlingGame nowTurnBowlingGame() {
        return values.stream()
                .filter(bowlingGame -> bowlingGame.round().equals(this.round) && !bowlingGame.isGameEnd())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BowlingGames that = (BowlingGames) o;
        return Objects.equals(values, that.values) && Objects.equals(round, that.round);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values, round);
    }

    @Override
    public String toString() {
        return "BowlingGames{" +
                "values=" + values +
                ", round=" + round +
                '}';
    }
}
