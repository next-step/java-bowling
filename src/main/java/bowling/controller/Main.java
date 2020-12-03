package bowling.controller;

import bowling.domain.Game;
import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.Roll;
import bowling.dto.FrameNoDto;
import bowling.dto.PlayerDto;
import bowling.view.View;

public class Main {
    private Main() {}

    public static void main(String[] args) {
        Game game = getGame();
        game.play();
    }

    private static Game getGame() {
        Game game = new Game();
        getPlayers().forEach(player -> game.addPlayer(player, () ->
                getRoll(game.exportFrameNoDto()) // getRoll(player.exportPlayerDto())
        ));
        game.register(() -> View.printGame(game.exportGameDto()));
        return game;
    }

    private static Players getPlayers() {
        return Players.of(
                1, // View.askSizeOfPlayers().getSize(),
                Main::getPlayer
        );
    }

    private static Player getPlayer() {
        return new Player(View.askPlayer()
                .getName()
        );
    }

    private static Roll getRoll(FrameNoDto frameNoDto) {
        return Roll.of(View.askRoll(frameNoDto)
                .getCountOfPins());
    }

    private static Roll getRoll(PlayerDto frameNoDto) {
        return Roll.of(View.askRoll(frameNoDto)
                .getCountOfPins());
    }
}
