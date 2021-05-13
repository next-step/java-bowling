package bowling.domain.player;

import bowling.domain.HitNumber;
import bowling.domain.Score;

import java.util.*;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class Players {
    private static final String INVALID_WINNING_STATE = "아직 게임이 종료되지 않아 우승자를 가릴 수 없습니다.";
    private final List<Player> players;
    private final Queue<Player> now;

    private Players(List<Player> players) {
        this.players = players;
        this.now = new LinkedList<>(this.players);
    }

    public static Players of(List<String> nameList) {
        return new Players(nameList.stream()
                .map(name -> Player.of(name))
                .collect(toList()));
    }

    public void play(int hitNumber) {
        Player nowPlayer = now.peek();
        validContinue(nowPlayer.playWithNext(HitNumber.of(hitNumber)), nowPlayer);
    }

    public String whoseTurn() {
        return now.peek().toStringName();
    }

    public boolean isFinished() {
        return players.stream()
                .noneMatch(player -> !player.isFinished());
    }

    public List<Player> getWinner() {
        validCanWinning();
        Score maxScore = players.stream()
                .map(player -> player.totalScore())
                .max(Score::compareTo)
                .orElseThrow(IllegalStateException::new);
        return players.stream()
                .filter(player -> Score.compareTo(
                        player.totalScore(), maxScore) == 0)
                .collect(toList());
    }

    public List<Player> getPlayers() {
        return unmodifiableList(players);
    }

    private void validContinue(Boolean isNext, Player nowPlayer) {
        if(!isNext && !nowPlayer.isFinished()) {
            return;
        }
        now.poll();
        if(!nowPlayer.isFinished()) {
            now.add(nowPlayer);
        }
    }

    private void validCanWinning() {
        if(!isFinished()) {
            throw new IllegalStateException(INVALID_WINNING_STATE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players) && Objects.equals(now, players1.now);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players, now);
    }

}
