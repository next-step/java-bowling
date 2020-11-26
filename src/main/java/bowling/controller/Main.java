package bowling.controller;

import bowling.domain.Players;
import bowling.view.OutputView;

public class Main {
    private Main() {}

    public static void main(String[] args) {
        int sizeOfPlayers = 1;
        Players players = ModelMapper.getPlayers(sizeOfPlayers);
        OutputView.printScoreBoard();
    }
}
