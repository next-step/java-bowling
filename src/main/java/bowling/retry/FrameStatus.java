package bowling.retry;

import java.util.LinkedList;

public class FrameStatus {

    private int chanceOfCount;
    private int firstHitOfCount;
    private int secondHitOfCount;

    private LinkedList<Pin> pins;

    public FrameStatus(LinkedList<Pin> pins) {
        this.pins = pins;
        this.chanceOfCount = pins.size();
        initiate();
    }

    private void initiate() {
        this.firstHitOfCount = pins.getFirst().getCountOfHit();
        if (chanceOfCount == 2) {
            this.secondHitOfCount = pins.getLast().getCountOfHit();
        }
    }

    public String getStatus() {
        if (chanceOfCount == 1) {
            return getStrike();
        }
        return getSpare();
    }

    private String getStrike() {
        if (isStrike()) {
            return "  X   |";
        }
        return getGutterByFirstStatus();
    }

    private String getGutterByFirstStatus() {
        if (firstHitOfCount == 0) {
            return "  -   |";
        }
        return "  " + firstHitOfCount + "   |";
    }

    private String getSpare() {
        if (isSpare()) {
            return "  "+firstHitOfCount+"|/ |";
        }
        return getGutterBySecondStatus();
    }

    private String getGutterBySecondStatus() {
        String firstHit = String.valueOf(firstHitOfCount);
        if (firstHitOfCount == 0) {
            firstHit = "-";
        }

        if (secondHitOfCount == 0) {

            return "  "+firstHit+"|- |";
        }
        return "  "+firstHit+"|"+secondHitOfCount+" |";

    }

    private boolean isSpare() {
        return firstHitOfCount + secondHitOfCount == 10;
    }

    private boolean isStrike() {
        return firstHitOfCount == 10;
    }
}
