package bowling;

public class Frame {
    private int score;

    public void setKnockDownPins(int knockDownPins) {
        score = knockDownPins;
    }

    public String getStatus() {
        if (score == 10) {
            return "X";
        }

        return String.valueOf(score);
    }
}
