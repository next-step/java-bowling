package bowling.controller;

import bowling.View;
import bowling.domain.player.Player;
import bowling.domain.player.Players;

public class Game {

    private Game() {
    }

    public static void start(View view) {
        try {
            Players players = new Players();
            applyPlayer(view, players, view.getPlayerCount());

            while (players.hasPlayablePlayer()) {
                playEach(view, players);
            }
        } catch(Exception exception) {
            view.showErrorMessage(exception.getMessage());
            view.showRestartMessage();

            start(view);
        }
    }

    private static void applyPlayer(View view, Players players, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Player 는 최소 1명 이상이어야 합니다.");
        }

        for (int i = 0; i < count; i++) {
            players.add(Player.create(view.getName()));
        }
    }

    private static void playEach(View view, Players players) {
        for (Player player : players.getValue()) {
            play(view, players, player);
        }
    }

    private static void play(View view, Players players, Player targetPlayer) {
        int lastPlayCount = targetPlayer.getPlayCount();

        while (targetPlayer.getPlayCount() == lastPlayCount) {
            int hitCount = view.getHitCount(targetPlayer.getName(), lastPlayCount);

            targetPlayer.play(hitCount);
            view.showFrameSetResult(players.getValue());
        }
    }
}
