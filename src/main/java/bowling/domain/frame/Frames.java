package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

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
        return getScores(INITIAL_SCORE, scores, FRAME_LIST_START_INDEX);
    }

    private List<Integer> getScores(int accumulatedScore, List<Integer> scores, int index){
        if(index >= getFrames().size()){
            return scores;
        }
        Frame frame = getFrames().get(index);
        int score = frame.score();
        if(score == Score.CANNOT_CALCULATE_SCORE){
            return scores;
        }
        accumulatedScore += score;
        scores.add(accumulatedScore);

        return getScores(accumulatedScore, scores, index+1);
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
