package bowling.domain.score;

/**
 * Created : 2020-12-21 오후 2:08
 * Developer : Seo
 */
public class TotalScore {
    private int totalScore = 0;

    public void add(int frameScore) {
         totalScore += frameScore;
    }

    public String get() {
        return String.valueOf(totalScore);
    }

    public String addAndGet(int num) {
        return String.valueOf(totalScore + num);
    }
}
