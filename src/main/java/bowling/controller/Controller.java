package bowling.controller;

import bowling.domain.frame.Frame;
import bowling.domain.Game;
import bowling.domain.Player;
import bowling.domain.KnockedPins;
import bowling.domain.frame.NormalFrame;
import bowling.domain.pitch.Pitch;
import bowling.view.Input;
import bowling.view.Output;

import java.util.Collections;


public class Controller {
    public static void main(String[] args) {
        Game game = Game.init();
        Player player = new Player(Input.inputPlayerName());
        Output.outputBoard(game, player);
        int frameNumber = 1;
        while (!game.isGameOver()) {
            game = game.play(new KnockedPins(Input.inputScore(frameNumber)));
            if (!game.getFrames().get(game.getFrames().size() - 1).isPlaying()) {
                frameNumber++;
            }
            Output.outputBoard(game, player);
        }
    }
}
