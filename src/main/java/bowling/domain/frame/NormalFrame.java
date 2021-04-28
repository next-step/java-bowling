package bowling.domain.frame;


import java.util.ArrayList;
import java.util.List;

public class NormalFrame extends Frame {
    public NormalFrame() {
        this.scores = new ArrayList<>();
    }

    public NormalFrame(List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public boolean isFinished() {
        if (scores.isEmpty()) {
            return false;
        }
        if (scores.size() == 2) {
            return true;
        }
        if (scores.get(0).equals(Score.STRIKE)) {
            return true;
        }
        return false;
    }

    @Override
    public void addScore(int score) throws Exception {
        if (isFinished()) {
            throw new Exception("종료된 프레임입니다.");
        }
        if (scores.size() == 0) {
            this.scores.add(Score.valueOf(score));
            return;
        }
        if (scores.size() == 1) {
            this.scores.add(Score.valueOf(scores.get(0), score));
            return;
        }
    }

    @Override
    public String toPrint() {
        StringBuilder sb = new StringBuilder();
        this.scores
                .forEach(score -> sb.append(score.getExpression()));

        if (scores.size() == 2) {
            sb.insert(1, "|");
        }

        String result = String.format("%-4s", sb.toString());
        result = String.format("%6s", result);

        return result;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
