package bowling.controller;

import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.domain.Players;
import bowling.dto.FrameNumberDto;
import bowling.view.View;

import static bowling.asset.Const.MAX_FRAME_NO;

class Game {
    private final Players players;
    private int frameNumber = 1;

    Game() {
        // int sizeOfPlayers = View.askSizeOfPlayers().getSize();
        int sizeOfPlayers = 1;
        players = Players.of(sizeOfPlayers, this::getPlayer);
    }

    void play() {
        while (frameNumber <= MAX_FRAME_NO) {
            players.forEach(this::play);
            frameNumber++;
        }
    }

    private void play(Player player) {
        while (player.isPlayable(frameNumber)) {
            player.addPin(
                    // getPin(player)
                    getPin(frameNumber)
            );
            View.printPlayers(players.exportPlayersDto());
        }
    }

    private Player getPlayer() {
        return new Player(View.askName()
                .getName());
    }

    private Pin getPin(int frameNumber) {
        return Pin.of(View.askPin(new FrameNumberDto(frameNumber))
                .getCountOfPins());
    }

    private Pin getPin(Player player) {
        return Pin.of(View.askPin(player.exportPlayerDto())
                .getCountOfPins());
    }
}
