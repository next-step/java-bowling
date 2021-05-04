package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private List<Bowling> bowlingGame;

    public BowlingGame(List<Bowling> bowlingGame) {
        this.bowlingGame = bowlingGame;
    }

    public static BowlingGame valueOf(List<User> users) {
        List<Bowling> bowlingGame = new ArrayList<>();
        users.forEach(user -> bowlingGame.add(new Bowling(user)));
        return new BowlingGame(bowlingGame);
    }

    public BowlingGame addScore(int score, int index) throws Exception {
        List<Bowling> result = new ArrayList<>(this.bowlingGame);
        result.set(index, result.get(index).addScore(score));
        return new BowlingGame(result);
    }

    public List<Bowling> getBowlingGame() {
        return this.bowlingGame;
    }

    public Bowling getBowling(int index) {
        return this.bowlingGame.get(index);
    }

    public int howManyPlayer() {
        return this.bowlingGame.size();
    }
}
