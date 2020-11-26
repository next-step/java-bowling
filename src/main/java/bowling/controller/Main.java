package bowling.controller;

import bowling.domain.CountOfPins;
import bowling.domain.Player;
import bowling.view.OutputView;

public class Main {
    private Main() {}

    public static void main(String[] args) {
        Player player = ModelMapper.getPlayer();
        CountOfPins countOfPins = ModelMapper.getCountOfPins(1);
        OutputView.printScoreBoard();
    }
}
