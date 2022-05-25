package bowling.domain;

public class Subtotal {
    private int score;
    private int waiting;

    public Subtotal(int score, int waiting) {
        this.score = score;
        this.waiting = waiting;
    }

    public static Subtotal create(Score score, int prevSubtotalScore) {
        if (score.hit.last()) {
            return new Subtotal(prevSubtotalScore + score.hit.third(), 0);
        }
        if (score.scoreType() == ScoreType.STRIKE) {
            return new Subtotal(prevSubtotalScore + score.hit.first(), 2);
        }
        if (score.scoreType() == ScoreType.SPARE) {
            return new Subtotal(prevSubtotalScore + score.hit.first() + score.hit.second(), 1);
        }
        if (score.scoreType() == ScoreType.MISS) {
            return new Subtotal(prevSubtotalScore + score.hit.first() + score.hit.second(), 0);
        }
        if (score.scoreType() == ScoreType.GUTTER) {
            return new Subtotal(prevSubtotalScore, 0);
        }
        throw new IllegalArgumentException("unreachable: " + score);
    }

    public Subtotal aggregate(int score) {
        // strike: wait for next first and second
        // spare: wait for next first only
        // miss: don't wait
        // gutter: kinda miss
        // second: null
//        if (score.scoreType() == ScoreType.STRIKE) {
//            return new Subtotal(this.score += score.hit.first(), 2 );
//        }
        return new Subtotal(this.score += score, this.waiting - 1);
    }

    public int score() {
        if (this.waiting > 0) {
            throw new RuntimeException("cannot get score yet");
        }
        return this.score;
    }

    public void evaluateBonus(Score nextScore) {
        if (waiting == 2 && nextScore.scoreType() == ScoreType.STRIKE) {
            this.score += nextScore.hit.first();
            this.waiting -= 2;
        }
        if (waiting == 2 && nextScore.done()) {
            this.score += nextScore.hit.first() + nextScore.hit.second();
            this.waiting -= 2;
        }
        if (waiting == 1) {
            this.score += nextScore.hit.first();
            this.waiting -= 1;
        }
    }

    public int waiting() {
        return this.waiting;
    }

    public void setLast() {
        this.waiting = 0;
    }
}
