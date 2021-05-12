package bowling.domain.player;

import bowling.domain.HitNumber;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Players {
    private final List<Player> players;
    private final Queue<Player> now;

    private Players() {
        players = new ArrayList<>();
        now = new LinkedList<>();
    }

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
        if(nowPlayer.bowlWithNext(HitNumber.of(hitNumber))) {
            return;
        }
        now.poll();
        validFinished(nowPlayer);
    }

    public String whoseTurn() {
        return now.peek().toStringName();
    }

    private void validFinished(Player nowPlayer) {
        if(!nowPlayer.isFinished()) {
            now.add(nowPlayer);
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
