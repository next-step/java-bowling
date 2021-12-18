package bowling;

public class Frame {

    private static final int MIN_ROUND = 0;
    private static final int MAX_ROUND = 9;
    private static final int MAX_OF_EACH_ROUND_SCORE = 10;
    private static final int MAX_OF_FINAL_ROUND_SCORE = 30;

    private final int round;
    private final Scores scores;

    public Frame(int round, Scores scores) {

        valid(round, scores);

        this.round = round;
        this.scores = scores;
    }

    private void valid(int round, Scores scores) {
        if (round < MIN_ROUND) {
            throw new IllegalArgumentException("round 는 음수가 올 수 없어요.");
        }

        if (round > MAX_ROUND) {
            throw new IllegalArgumentException("round 는 9를 넘길 수 없어요.");
        }

        if (round != MAX_ROUND && scores.sumScore() > MAX_OF_EACH_ROUND_SCORE) {
            throw new IllegalArgumentException("최종 라운드가 아니면, 10점을 넘길 수 없어요.");
        }

        if (round == MAX_ROUND && scores.sumScore() > MAX_OF_FINAL_ROUND_SCORE) {
            throw new IllegalArgumentException("최종 라운드는, 30점을 넘길 수 없어요.");
        }

    }

}
