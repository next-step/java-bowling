package bowling;

public class Result {
    private ResultScore resultScore;
    private String name;

    public Result(ResultScore resultScore, String name) {
        this.resultScore = resultScore;
        this.name = name;
    }

    public String name() {
        return name;
    }
}
