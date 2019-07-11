package domain;

public class ScoreBoard {

    private PlayerName playerName;
    private Scores scores;

    private ScoreBoard(PlayerName playerName, Scores scores) {
        this.playerName = playerName;
        this.scores = scores;
    }

    public static ScoreBoard of(PlayerName playerName, Scores scores) {
        return new ScoreBoard(playerName, scores);
    }

    public String printBoard() {
        String nameFrame = "|  " + playerName.getName() + " |";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(nameFrame);
        for (Score score : scores) {
            stringBuilder.append("  " + score + "   |");
        }
        return stringBuilder.toString();
    }
}
