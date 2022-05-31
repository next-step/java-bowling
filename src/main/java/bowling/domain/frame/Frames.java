package bowling.domain.frame;

import bowling.domain.pin.Pins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private static final int FRAME_LIST_START_INDEX = 0;
    private static final int INITIAL_SCORE = 0;
    private static final int MIN_FRAME_INDEX = 1;

    private List<Frame> frames;

    public Frames(){
        frames = new ArrayList<>();
        frames.add(new NormalFrame(MIN_FRAME_INDEX));
    }

    public void pitch(Pins pins){
        Frame nextFrame = getCurFrame().pitch(pins);

        if(nextFrame != getCurFrame()){
            frames.add(nextFrame);
        }
    }

    public List<Integer> getScores(){
        List<Integer> scores = new ArrayList<>();
        Frame frame = getCurFrame();
        int accumulatedScore = 0;
        while(frame.isEnd() && frame.hasNext()){
            accumulatedScore += frame.score();
            scores.add(accumulatedScore);
            frame = frame.getNextFrame();
        }

        if(!frame.hasNext() && frame.isEnd()){
            accumulatedScore += frame.score();
            scores.add(accumulatedScore);
        }
        return scores;
    }

    public int size(){
        return frames.size();
    }

    public Frame getCurFrame(){
        return frames.get(size()-1);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

}
