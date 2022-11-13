package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Frames {
    private final List<Frame> frames;
    private boolean isProgress = true;

    public Frames(Frame... frames) {
        this.frames = new ArrayList<>(Arrays.asList(frames));
    }

    public List<Frame> getFrames() {
        return frames;
    }

    private Frame getLastFrame(){
        return frames.get(frames.size() - 1);
    }

    public int getLastOrder(){
        return getLastFrame().getOrder();
    }

    public int getLastTry(){
        return getLastFrame().getLatestScore();
    }

    public void next(){
        isProgress = !isEnd();
        if(getLastFrame().isProgress()) {
            nextTry();
            return;
        }
        nextRound();
    }

    public void nextTry(){
        getLastFrame().nextTry();
    }

    public void nextRound(){
        frames.add(getLastFrame().nextRound());
    }

    private boolean isEnd() {
        return frames.size() == 10 && !getLastFrame().isProgress();
    }

    public boolean isProgress(){
        return isProgress;
    }

    public int size(){
        return frames.size();
    }
}
