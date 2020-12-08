package bowling;

public class Frame {
    private int score;
    private Integer firstPitching;
    private Integer secondPitching;

    public void setKnockDownPins(int knockDownPins) {
        if (firstPitching == null) {
            firstPitching = knockDownPins;
            return;
        }

        secondPitching = knockDownPins;
    }

    public String getStatus() {
        if (firstPitching == 10) {
            return "X";
        }

        return firstPitching + "|" + secondPitching;
    }
}
