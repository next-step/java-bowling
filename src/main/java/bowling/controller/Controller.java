package bowling.controller;

import bowling.domain.Board;
import bowling.domain.Game;
import bowling.domain.Player;
import bowling.domain.KnockedPins;
import bowling.view.Input;
import bowling.view.Output;


public class Controller {
    public static void main(String[] args) {
        Game game = new Game(new Board());
        game.init();
        Player player = new Player(Input.inputPlayerName());
        Output.outputBoard(game, player);
        while (!game.isGameOver()) {
            game.playGame(new KnockedPins(Input.inputScore(game)));
            Output.outputBoard(game, player);
        }
    }
}
