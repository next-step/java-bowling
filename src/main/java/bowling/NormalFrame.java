package bowling;

import java.util.LinkedList;
import java.util.List;

public class NormalFrame implements Frame {
    public static final int NORMAL_FRAME_MAX_PITCHING_SIZE = 2;
    private int index;
    private int score;
    private LinkedList<Pitching> pitchings;
    private Integer firstPitching;
    private Integer secondPitching;

    public NormalFrame() {
    }

    public NormalFrame(int index) {
        this.index = index;
        pitchings = new LinkedList<>();
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
    public void setKnockDownPins2(int knockDownPins) {
        if (pitchings.isEmpty()) {
            pitchings.add(Pitching.getPitching(knockDownPins));
            return;
        }

        Pitching previousPitching = pitchings.getLast();
        pitchings.add(Pitching.getPitching(knockDownPins, previousPitching));
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
    public List<Pitching> getStatus2(){
        return pitchings;
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

    public boolean isEnd2() {
        if (pitchings.isEmpty()) {
            return false;
        }

        if (pitchings.getFirst() == Pitching.STRIKE) {
            return true;
        }

        return pitchings.size() == NORMAL_FRAME_MAX_PITCHING_SIZE;
    }
}
