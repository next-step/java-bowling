package bowling.domain;

public enum ScoreText {
    GUTTER(0, "-"),
    ONE(1,"1"),
    TWO(2,"2"),
    THREE(3,"3"),
    FOUR(4,"4"),
    FIVE(5,"5"),
    SIX(6,"6"),
    SEVEN(7,"7"),
    EIGHT(8,"8"),
    NINE(9,"9"),
    STRIKE(10,"X");

    private int score;
    private String scoreText;

    ScoreText(int score, String scoreText) {
        this.score = score;
        this.scoreText = scoreText;
    }

    public int getScore() {
        return score;
    }

    public String getScoreText() {
        return scoreText;
    }

    public static String getScoreTextByScore(int score) {
        for(ScoreText scoreText : values()) {
            if(scoreText.getScore() == score){
                return scoreText.getScoreText();
            }
        }
        throw new IllegalArgumentException("허용값을 벗어난 스코어입니다.");
    }
}
