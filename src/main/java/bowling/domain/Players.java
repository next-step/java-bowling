package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;
    private Player currentPlayer;

    private Players(List<Player> players) {
        this.players = players;
        currentPlayer = players.get(0);
    }

    public static Players of(List<String> players) {
        if (isPlayerNamesNullOrEmpty(players)) {
            throw new IllegalArgumentException("Create Players failed. players must have at least 1 player");
        }

        if (isPlayersHaveSameName(players)) {
            throw new IllegalArgumentException("Create Players failed. players are must have not same name");
        }

        return new Players(players.stream().map(Player::of).collect(Collectors.toList()));
    }

    static Players of(Player... players){
        return new Players(Arrays.asList(players));
    }

    private static boolean isPlayersHaveSameName(List<String> players) {
        return players.stream().distinct().count() != players.size();
    }

    private static boolean isPlayerNamesNullOrEmpty(List<String> players) {
        return players == null || players.size() < 1;
    }

    public void shot(int shot) {
        currentPlayer.shot(shot);
        currentPlayer = getCurrentPlayer();
    }

    public String getCurrentPlayerName() {
        return currentPlayer.name();
    }

    private Player getCurrentPlayer() {
        return players.stream()
                .filter(v -> v.getCurrentFrameNumber() == getMinimumFrameNumber())
                .min(Comparator.comparing(Player::getCurrentFrameShotCount))
                .get();
    }

    private int getMinimumFrameNumber() {
        return players.stream()
                .mapToInt(Player::getCurrentFrameNumber)
                .min()
                .orElse(0);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public boolean isGameSet(){
        return players.stream().allMatch(Player::isGameSet);
    }
}
