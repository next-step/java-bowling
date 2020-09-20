package bowling;

public class NormalFrame implements Frame{
    private Scores scores;

    private int NORMAL_TRY_COUNT = 2;
    private int tryCount;

    private final BowlingPin bowlingPin;

    private NormalFrame() {
        this.scores = Scores.init();
        this.bowlingPin = BowlingPin.getInitialPin();
    }

    public static NormalFrame of() {
        return new NormalFrame();
    }

    @Override
    public void play(int numberOfFallenPin){
        increaseTryCount();
        bowlingPin.roll(numberOfFallenPin);


        Score score = Score.of(numberOfFallenPin, isSpare());
        scores.add(score);
    }


    private boolean isSpare(){
        if(tryCount == 2 && bowlingPin.isAllFallen()){
            return true;
        }
        return false;
    }

    private void increaseTryCount(){
        tryCount++;
        if(tryCount > NORMAL_TRY_COUNT) throw new IllegalArgumentException("Exceed max try count");
    }

    @Override
    public boolean isTerminate(){
        return bowlingPin.isAllFallen() || tryCount == Frame.MAX_TRY_COUNT;
    }

    @Override
    public String getScores() {
        return scores.toString();
    }
}
