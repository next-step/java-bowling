package bowling.domain;

public class NormalFrame extends Frame{

    public FrameStatus status = FrameStatus.READY;
    private int score;

    private Frame before;
    private Frame next;

    @Override
    public int score() {
        return 0;
    }

    public void firstShot(PinScore pinScore){
        setFirstStatus(pinScore);
        this.score = pinScore.value();
    }

    public void secondShot(PinScore pinScore){
        setSecondStatus(pinScore);
        this.score += pinScore.value();
    }

    private void setFirstStatus(PinScore score){
        if(PinScore.MAX == score.value()){

        }
    }
}
