package bowling.domain;

import bowling.domain.scorestrategy.ScoreStrategy;

import java.util.Collections;
import java.util.List;

public class Players {
    private List<Player> players;
    private int nowTurnPlayerIndex;

    public Players(List<Player> players) {
        this.players = players;
        this.nowTurnPlayerIndex = 0;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public boolean isGaming() {
        return !players.stream().allMatch(Player::isFinished);
    }

    public Player play(ScoreStrategy scoreStrategy) {

        makeNextFrame();
        checkAndSkipPlayerIndex();

        Player now = players.get(nowTurnPlayerIndex);
        now.play(scoreStrategy);

        increasePlayerIndex();

        return now;
    }

    private void increasePlayerIndex() {
        nowTurnPlayerIndex++;

        if (nowTurnPlayerIndex >= players.size()) {
            nowTurnPlayerIndex = 0;
        }
    }

    private void checkAndSkipPlayerIndex() {
        if (players.get(nowTurnPlayerIndex).isEndOfTurn()) {
            nowTurnPlayerIndex++;
        }
    }

    private void makeNextFrame() {
        if (!isEndOfTurn()) {
            return;
        }
        players.forEach(Player::next);
        nowTurnPlayerIndex = 0;
    }

    private boolean isEndOfTurn() {
        return players.stream().allMatch(Player::isEndOfTurn);
    }
}
