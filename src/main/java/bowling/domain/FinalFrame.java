package bowling.domain;

public class FinalFrame extends Frame{

    private static final int MAX_PITCH_COUNT = 3;

    private FinalFrame(int index){
        super(index);
    }

    public static FinalFrame from(int index){
        return new FinalFrame(index);
    }

    @Override
    public void start(PitchStrategy pitchStrategy) {
        if(!isEnd()){
            int pitchResult = pitchStrategy.makePitchResult(countLeftOverPins());
            pitchResults.addNewResult(pitchResult);
        }
    }

    private int countLeftOverPins() {
        int currentPoint = sumCurrentPitchResults();

        if(currentPoint % BOWLING_PIN_COUNT == 0){
            return BOWLING_PIN_COUNT;
        }

        if(currentPoint > BOWLING_PIN_COUNT){
            currentPoint = currentPoint - BOWLING_PIN_COUNT;
        }

        return BOWLING_PIN_COUNT - currentPoint;
    }


    @Override
    public boolean isEnd() {
        return (pitchResults.size() == 2 && sumCurrentPitchResults() == 0) ||
                (pitchResults.size() == 2 && countLeftOverPins() > 0 && countLeftOverPins() != 10) ||
                pitchResults.size() == MAX_PITCH_COUNT;
    }

    @Override
    public Frame makeNextFrame(int currentFrameIndex) {
        throw new RuntimeException("마지막 프레임입니다.");
    }

}
