package bowling.domain;

import static bowling.util.Const.MAX_PIN;
import static bowling.util.Const.NULL;
import static bowling.view.Output.format;

public class Score {
    private final ScoreType scoreType;
    final Hit hit;

    public Score() {
        this.hit = new Hit(NULL, NULL);
        this.scoreType = ScoreType.NULL;
    }

    public Score(int first) {
        this.hit = new Hit(first);
        this.scoreType = ScoreType.of(first);
    }

    public Score(int first, int second) {
        this.hit = new Hit(first, second);
        this.scoreType = ScoreType.of(first, second);
    }

    public Score(Score prevScore, int hit) {
        this.scoreType = prevScore.scoreType;
        this.hit = new Hit(prevScore.hit, hit);
    }

    public static Score play(Score prevScore) {
        if (prevScore.isPresent()) {
            int hit = Player.pitch(prevScore.hit.remainingPin());
            return new Score(prevScore.hit.first(), hit);
        }
        int hit = Player.pitch(MAX_PIN);
        return new Score(hit);
    }

    public static Score playBonus(Score prevScore) {
        int hit = Player.pitch(MAX_PIN);
        return new Score(prevScore, hit);
    }

    private boolean isPresent() {
        return this.scoreType != ScoreType.NULL;
    }


    public static String scoreBoard(Score score) {
        if (score.hit.last()) {
            return format(score.hit.first() == 10  && score.hit.third() == 10? "XXX" : scoreBoard(score.previous()).trim() + "|" + score.hit.thirdStr());
        }
        if (score.scoreType == ScoreType.MISS) {
            return format(score.hit.firstStr() + score.scoreType.toSymbol() + score.hit.secondStr());
        }
        if (score.scoreType == ScoreType.SPARE) {
            return format(score.hit.first() + score.scoreType.toSymbol());
        }
        if (score.scoreType == ScoreType.SECOND) {
            return format(score.hit.firstStr());
        }
        return format(score.scoreType.toSymbol());
    }

    private Score previous() {
        if (this.hit.hasSecond()) {
            return new Score(this.hit.first(), this.hit.second());
        }
        return new Score(this.hit.first());
    }

//    public static String format(String string) {
//        return String.format("%-4s", string);
//    }

    public boolean done() {
        return this.scoreType != ScoreType.SECOND;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreType=" + scoreType +
                ", hit=" + hit +
                "}\n";
    }

    public ScoreType scoreType() {
        return this.scoreType;
    }
}
