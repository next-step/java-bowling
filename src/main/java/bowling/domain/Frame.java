package bowling.domain;

public class Frame {

    private Frame next;
    private Score score;
    private int order;

    public Frame(int order) {
        this.order = order;
        this.score = (order == 10)? new FinalScore() : new EmptyScore();
    }

    public Frame ofNext() {
        Frame newFrame = new Frame(order + 1);
        next = newFrame;
        return newFrame;
    }

    public boolean isFinished() {
        return score.isFinished();
    }

    public void bowl(int pin) {
        score = score.ofNext(pin);
    }

    public Score getScore(){
        return score;
    }

}
