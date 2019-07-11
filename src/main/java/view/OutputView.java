package view;

import domain.PlayerName;

public class OutputView {

    public static void printScoreBoard(PlayerName playerName) {
        System.out.println(playerName.getName());
    }
}
