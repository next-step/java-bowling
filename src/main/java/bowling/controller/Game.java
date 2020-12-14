package bowling.controller;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.frame.Pin;
import bowling.view.View;

class Game {
    private final Players players;

    Game() {
        // int sizeOfPlayers = View.askSizeOfPlayers().getSize();
        int sizeOfPlayers = 1;
        players = Players.of(sizeOfPlayers, this::getPlayer);
    }

    void play() {
        while (players.isPlayable()) {
            players.forEach(this::play);
        }
    }

    private void play(Player player) {
        while (player.isPlayable()) {
            player.addPin(getPin(player));
            View.printPlayers(players.exportPlayersDto());
        }
        player.updateBowl();
    }

    private Player getPlayer() {
        return new Player(View.askName()
                .getName());
    }

    private Pin getPin(Player player) {
        return Pin.of(View.askPin(player.exportAskPinDto())
                .getCountOfDownPins());
    }
}
