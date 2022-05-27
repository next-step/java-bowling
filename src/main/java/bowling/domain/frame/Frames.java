package bowling.domain.frame;

import bowling.domain.pin.Pins;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

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
        int accumulatedScore = 0;
        for(Frame frame: frames){
            int score = frame.score();
            if(score == -1){
                return scores;
            }
            accumulatedScore += score;
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
