package bowling.controller;

import bowling.model.Player;
import bowling.model.frame.Frames;
import bowling.view.InputView;

public class BowlingController {

    public void run(){
        Player player = new Player(InputView.inputPlayerName());
        Frames frames = new Frames();
    }
}
