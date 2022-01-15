package bowling;

import bowling.frame.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BowlingGames {

    private final List<BowlingGame> bowlingGames = new ArrayList<>();
    private final int numberOfPlayer;

    public BowlingGames(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public void enter(Player player) {
        if (bowlingGames.size() == numberOfPlayer) {
            throw new UnsupportedOperationException("참가자 수가 초과하였습니다. 명단에 없는 분은 참가하실 수 없습니다.");
        }
        this.bowlingGames.add(new BowlingGame(player));
    }

    public int numberOfPlayers() {
        return bowlingGames.size();
    }

    public void bowl(Pins fallenPins) {
        if (bowlingGames.size() < numberOfPlayer) {
            throw new UnsupportedOperationException("참가자가 모두 입장하지 않았습니다. 입장 후 시작할 수 있습니다.");
        }
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

    public int getNumberOfPlayer() {
        return numberOfPlayer;
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
