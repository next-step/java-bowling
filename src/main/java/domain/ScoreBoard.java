package domain;

public class ScoreBoard {

    private PlayerName playerName;


    private ScoreBoard(PlayerName playerName) {
        this.playerName = playerName;
    }

    public static ScoreBoard of(PlayerName playerName) {
        return new ScoreBoard(playerName);
    }

    public String printBoard() {
        String nameFrame = "|  " + playerName.getName() + " |";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(nameFrame);
//        for (Score score : scores) {
//            stringBuilder.append("  " + score + "   |");
//        }
        return stringBuilder.toString();
    }
}
