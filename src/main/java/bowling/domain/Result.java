package bowling.domain;

public class Result {

    private final int score;
    private final String desc;

    public Result(int score, String desc) {
        this.score = score;
        this.desc = desc;
    }

    public int getScore() {
        return score;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Result{" +
                "score=" + score +
                ", desc='" + desc + '\'' +
                '}';
    }
}
