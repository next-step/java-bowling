package bowling.domain;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class FrameHistory {
    private final List<PitchHistory> pitchHistories = new ArrayList<>();
    private FrameHistory nextFrameHistory = null;

    public void record(PitchResult pitchResult, int downPinCount) {
        PitchHistory pitchHistory = new PitchHistory(pitchResult, downPinCount);
        FrameHistory lastHistory = getLastHistory();
        lastHistory.addPitchHistory(pitchHistory);
    }

    public FrameHistory getNextFrameHistory() {
        return nextFrameHistory;
    }

    public List<PitchHistory> getPitchHistories() {
        return pitchHistories;
    }

    public int getLastIndex(){
        return getLastIndex(0);
    }

    public void addHistory(){
        if(CollectionUtils.isEmpty(pitchHistories)){
            return;
        }
        FrameHistory lastHistory = getLastHistory();
        lastHistory.nextFrameHistory = new FrameHistory();
    }

    private FrameHistory getLastHistory(){
        if(this.nextFrameHistory == null){
            return this;
        }
        FrameHistory nextFrameHistory = this.nextFrameHistory;
        return nextFrameHistory.getLastHistory();
    }

    private int getLastIndex(int currentIndex){
        if(nextFrameHistory == null){
            return currentIndex;
        }
        currentIndex++;
        return nextFrameHistory.getLastIndex(currentIndex);
    }

    private void addPitchHistory(PitchHistory pitchHistory){
        pitchHistories.add(pitchHistory);
    }

}
