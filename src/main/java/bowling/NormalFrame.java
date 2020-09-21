package bowling;

public class NormalFrame implements Frame{
    private Scores scores;

    private final int currentIndex;

    private final BowlingPin bowlingPin;

    private NormalFrame(int currentIndex) {
        this.scores = Scores.init();
        this.bowlingPin = BowlingPin.getInitialPin();
        this.currentIndex = currentIndex;
    }

    public static NormalFrame of(int currentIndex) {
        return new NormalFrame(currentIndex);
    }

    @Override
    public Frame play(int numberOfFallenPin){
        bowlingPin.roll(numberOfFallenPin);


        Score score = Score.of(numberOfFallenPin, isSpare());
        scores.add(score);


        if (isTerminate()) { //현재 frame이 끝났으면 index 증가 시키고 다음 frame을 리턴
            return getNext();
        }
        return this;
    }

    private void checkScoreLength(){
        if(scores.getSize() > Frame.MAX_TRY_COUNT){
            throw new IllegalArgumentException("exceed max try count");
        }
    }
    private boolean isSpare(){
        if(scores.getSize() == 2 && bowlingPin.isAllFallen()){
            return true;
        }
        return false;
    }

    @Override
    public boolean isTerminate(){
        return bowlingPin.isAllFallen() || scores.getSize() == Frame.MAX_TRY_COUNT;
    }

    @Override
    public String getScores() {
        return scores.toString();
    }

    public Frame getNext(){
        if(isPrevFinalFrame()){
            return LastFrame.of();
        }
        return NormalFrame.of(currentIndex+1);
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }

    private boolean isPrevFinalFrame(){
        return Frame.NUM_OF_FRAMES-1 == currentIndex;
    }


}
