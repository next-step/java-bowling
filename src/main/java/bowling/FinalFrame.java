package bowling;

import java.util.Stack;

public class FinalFrame implements Frame{

    private Stack<BowlingPin> bowlingPins = new Stack<>();
    private Scores scores;

    private int tryCount;
    private int NORMAL_TRY_COUNT = 2;

    public FinalFrame() {
        this.scores = Scores.init();
        bowlingPins.push(BowlingPin.getInitialPin());
    }

    public static FinalFrame of() {
        return new FinalFrame();
    }

    @Override
    public void play(int numberOfFallenPin) {
        if(!isTerminate()){
            bowlingPins.push(BowlingPin.getInitialPin());
        }

        BowlingPin lastBowlingPin = bowlingPins.pop();
        lastBowlingPin.roll(numberOfFallenPin);

        bowlingPins.push(lastBowlingPin);

        Score score = Score.of(numberOfFallenPin, isSpare());
        scores.add(score);

        increaseTryCount();
    }

    private boolean isSpare(){
        BowlingPin lastBowlingPin = bowlingPins.peek();
        if(tryCount == 2 && lastBowlingPin.isAllFallen()){
            return true;
        }
        return false;
    }

    private void increaseTryCount() {
        tryCount++;
    }

    public boolean isTerminate(){
        BowlingPin lastBowlingPin = bowlingPins.peek();
        return  (tryCount >= 2 && !lastBowlingPin.isAllFallen());
    }

    @Override
    public String getScores() {
        return scores.toString();
    }
}
