package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players = new ArrayList<>();
    private int nextBowlNumber;

    public void addPlayer(Player player) {
        players.add(player);
        this.nextBowlNumber = 0;
    }


    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public String getNextPlayerName() {
        int prevNumber = nextBowlNumber;
        while (players.get(nextBowlNumber).isFinished()) {
            nextBowlNumber = getNextNumber(prevNumber);
        }
        return players.get(nextBowlNumber).getName();
    }

    private int getNextNumber(int prevNumber) {
        int nextNumber = (nextBowlNumber + 1) % players.size();
        if (nextNumber == prevNumber) {
            throw new RuntimeException("모든 플레이어가 볼링을 완료하였습니다.");
        }
        return nextNumber;
    }

    public void bowl(Pin pin) {
        players.get(nextBowlNumber).bowl(pin);
        nextBowlNumber = getNextNumber(-1);
    }

    public boolean areNotFinished() {
        return players.stream()
                .anyMatch(player -> !player.isFinished());
    }

}
