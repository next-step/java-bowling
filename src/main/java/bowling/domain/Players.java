package bowling.domain;

import bowling.dto.PlayResult;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players){
        validateSize(players);
        this.players = players;
    }

    private void validateSize(List<Player> players) {
        if(players.isEmpty()){
            throw new IllegalArgumentException("player수가 너무 적습니다.");
        }
    }

    public Players(List<String> names, int totalNumberOfFrame) {
        this(names.stream()
                .map(name -> new Player(name,totalNumberOfFrame))
                .collect(Collectors.toList()));
    }

    public void play(int pintCount) {
        findLeastPlayedPlayer().addPinCount(pintCount);
    }

    public boolean isDone() {
        return players.stream()
                .allMatch(Player::isDone);
    }

    public Player playingPlayer() {
        return findLeastPlayedPlayer();
    }

    private Player findLeastPlayedPlayer() {
        return players.stream()
                .filter(player -> !player.isDone())
                .min(Comparator.comparing(Player::currentFrameNumber))
                .orElse(players.get(0));
    }

    public List<PlayResult> playResults() {
        return players.stream()
                .map(Player::playResult)
                .collect(Collectors.toList());
    }
}
