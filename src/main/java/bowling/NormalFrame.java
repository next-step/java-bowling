package bowling;

public class NormalFrame implements Frame {
    private int index;
    private int score;
    private Integer firstPitching;
    private Integer secondPitching;

    public NormalFrame(int index) {
        this.index = index;
    }

    @Override
    public void setKnockDownPins(int knockDownPins) {
        if (firstPitching == null) {
            firstPitching = knockDownPins;
            return;
        }

        secondPitching = knockDownPins;
    }

    @Override
    public String getStatus() {
        if (firstPitching == null) {
            return "";
        }

        if (firstPitching == 10) {
            return "X";
        }

        if (secondPitching == null) {
            if (firstPitching == 0) {
                return "-";
            }
            return String.valueOf(firstPitching);
        }

        if (firstPitching + secondPitching == 10) {
            return firstPitching + "|/";
        }

        if (secondPitching == 0) {
            return firstPitching + "|-";
        }

        return firstPitching + "|" + secondPitching;
    }

    @Override
    public boolean isEnd() {
        if (firstPitching == null) {
            return false;
        }

        if (firstPitching == 10) {
            return true;
        }

        return secondPitching != null;
    }
}
