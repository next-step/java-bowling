package bowling;

import java.util.Stack;

public class LastFrame implements Frame{

    private Stack<BowlingPin> bowlingPins = new Stack<>();
    private Scores scores;

    public LastFrame() {
        this.scores = Scores.init();
        bowlingPins.push(BowlingPin.getInitialPin());
    }

    public static LastFrame of() {
        return new LastFrame();
    }

    @Override
    public LastFrame play(int numberOfFallenPin) {
        if(!isTerminate()){
            bowlingPins.push(BowlingPin.getInitialPin());
        }

        BowlingPin lastBowlingPin = bowlingPins.pop();
        lastBowlingPin.roll(numberOfFallenPin);

        bowlingPins.push(lastBowlingPin);

        Score score = Score.of(numberOfFallenPin, isSpare());
        scores.add(score);

        return this;
    }

    private boolean isSpare(){
        BowlingPin lastBowlingPin = bowlingPins.peek();
        if(scores.getSize() == 2 && lastBowlingPin.isAllFallen()){
            return true;
        }
        return false;
    }

    public boolean isTerminate(){
        return  isExceedMaxTryCount() && isLeftBowlingPin();
    }

    private boolean isExceedMaxTryCount(){
        return scores.getSize() >= 2;
    }

    private boolean isLeftBowlingPin(){
        BowlingPin lastBowlingPin = bowlingPins.peek();
        return lastBowlingPin.isAllFallen();
    }

    @Override
    public String getScores() {
        return scores.toString();
    }

    @Override
    public Frame getNext() {
        throw new IllegalArgumentException("this is fianl frame");
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }
}
