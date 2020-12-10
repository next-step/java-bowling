package bowling;

import java.util.LinkedList;
import java.util.List;

public class NormalFrame implements Frame {
    public static final int NORMAL_FRAME_MAX_PITCHING_SIZE = 2;
    private final int index;
    private final LinkedList<Pitching> pitchings;

    public NormalFrame(int index) {
        this.index = index;
        pitchings = new LinkedList<>();
    }

    @Override
    public void setKnockDownPins(int knockDownPins) {
        if (pitchings.isEmpty()) {
            pitchings.add(Pitching.getPitching(knockDownPins));
            return;
        }

        Pitching previousPitching = pitchings.getLast();
        pitchings.add(Pitching.getPitching(knockDownPins, previousPitching));
    }

    @Override
    public List<Pitching> getStatus(){
        return pitchings;
    }

    public boolean isEnd() {
        if (pitchings.isEmpty()) {
            return false;
        }

        if (pitchings.getFirst() == Pitching.STRIKE) {
            return true;
        }

        return pitchings.size() == NORMAL_FRAME_MAX_PITCHING_SIZE;
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", pitchings=" + pitchings +
                '}';
    }
}
