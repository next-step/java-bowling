package bowling.domain;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.frame.ShootScore;
import bowling.score.Scores;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

public class Lane {

    private static final int FIRST_PLAYER = 0;

    private final List<BowlingGame> bowlingGames = new ArrayList<>();
    private int shootPlayerNumber;

    private Lane(List<PlayerName> playerNames) {
        playerNames.forEach(playerName ->
                bowlingGames.add(BowlingGame.from(playerName, Frames.create(), Scores.create()))
        );
        this.shootPlayerNumber = FIRST_PLAYER;
    }

    public static Lane from(List<PlayerName> playerNames) {
        return new Lane(playerNames);
    }

    public List<BowlingGame> bowlingGames() {
        return unmodifiableList(bowlingGames);
    }

    public String currentPlayer() {
        return bowlingGames
                .get(shootPlayerNumber)
                .playerName();
    }

    public boolean isEnd() {
        return bowlingGames.stream()
                .filter(bowlingGame -> !bowlingGame.isEnd())
                .findAny()
                .isEmpty();
    }

    public void shoot(ShootScore shootScore) {
        BowlingGame currentPlayerGame = bowlingGames.get(shootPlayerNumber);

        Frame frame = currentPlayerGame.shoot(shootScore);
        if (frame.isEnd()) {
            goNextPlayer();
        }

    }

    private void goNextPlayer() {
        if (shootPlayerNumber == bowlingGames.size() - 1) {
            shootPlayerNumber = FIRST_PLAYER;
            return;
        }

        shootPlayerNumber++;
    }
}
