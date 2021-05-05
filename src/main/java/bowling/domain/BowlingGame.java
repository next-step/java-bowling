package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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

    public BowlingGame addScore(int score) throws Exception {
        List<Bowling> result = new ArrayList<>(this.bowlingGame);
        result.set(nowPlayerOrder(), result.get(nowPlayerOrder()).addScore(score));
        return new BowlingGame(result);
    }

    public User nowPlayer() {
        return bowlingGame.get(nowPlayerOrder()).getPlayer();
    }

    public boolean isFinished() {
        return bowlingGame.stream()
                .map(bowling -> bowling.isFinished())
                .filter(aBoolean -> aBoolean == false)
                .findFirst()
                .orElse(true);

    }

    private Integer nowPlayerOrder() {
        return IntStream.range(0, bowlingGame.size())
                .filter(i -> bowlingGame.get(i).nowFrame() == nowMinFrame())
                .findFirst()
                .getAsInt();
    }

    private Integer nowMinFrame() {
        return bowlingGame.stream()
                .mapToInt(bowling -> bowling.nowFrame())
                .min()
                .getAsInt();
    }

    public List<Bowling> getBowlingGame() {
        return this.bowlingGame;
    }
}
