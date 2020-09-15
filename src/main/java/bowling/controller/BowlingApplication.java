package bowling.controller;

public class BowlingApplication {

    public static void main(String[] args) {
        BowlingSimulator bowlingSimulator = new BowlingConsoleSimulator();
        bowlingSimulator.readPlayerName();
        bowlingSimulator.initScoreBoard();
        bowlingSimulator.printScoreBoardStatus();
        bowlingSimulator.simulate();
    }
}
