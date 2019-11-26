package bowling.controller;

import bowling.domain.Game;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Controller {

    public static void main(String[] args) {
        InputView input = new InputView();
        OutputView output = new OutputView();

        Game game = new Game(input.getName());
        output.printTable(game.getTable());

        while(game.hasNext()){
            int next = game.getNext();
            game.bowl(input.getBowl(next));
            output.printTable(game.getTable());
        }
    }
}
