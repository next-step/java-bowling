package bowling;

import bowling.frame.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BowlingGames {

    private final List<BowlingGame> bowlingGames = new ArrayList<>();


    public void enter(Player player) {
        this.bowlingGames.add(new BowlingGame(player));
    }

    public int numberOfPlayers() {
        return bowlingGames.size();
    }

    public void bowl(Pins fallenPins) {
        currentTurn().bowl(fallenPins);
    }

    public boolean hasNextGame() {
        for (BowlingGame bowlingGame : bowlingGames) {
            if (bowlingGame.hasNextBowl()) {
                return true;
            }
        }
        return false;
    }

    public String getTurnPlayerName() {
        return currentTurn().getPlayerName();
    }

    public List<BowlingGame> getBowlingGames() {
        return Collections.unmodifiableList(bowlingGames);
    }

    BowlingGame currentTurn() {
        if (minimumFrameNo() == Frame.MAX_FRAME_NO) {
            return bowlingGames.stream()
                    .filter(bowlingGame -> !bowlingGame.isEnd())
                    .findFirst()
                    .get();
        }
        return bowlingGames.stream()
                .filter(bowlingGame -> bowlingGame.recentFrameNo() == minimumFrameNo())
                .findFirst()
                .get();
    }

    private int minimumFrameNo() {
        return bowlingGames.stream()
                .mapToInt(BowlingGame::recentFrameNo)
                .min()
                .getAsInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGames that = (BowlingGames) o;
        return Objects.equals(bowlingGames, that.bowlingGames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowlingGames);
    }

    @Override
    public String toString() {
        return "BowlingGames{" +
                "bowlingGames=" + bowlingGames +
                '}';
    }
}
