package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.status.Ready;
import bowling.domain.status.Spare;
import bowling.domain.status.Status;
import bowling.domain.status.Strike;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

    private final List<Status> statuses;

    private FinalFrame(List<Status> statuses) {
        this.statuses = statuses;
    }

    public static FinalFrame init() {
        List<Status> statuses = new ArrayList<>();
        statuses.add(new Ready());
        return new FinalFrame(statuses);
    }

    @Override
    public Frame bowl(Pin pin) {
        int curIdx = statuses.size() - 1;
        status = statuses.get(curIdx).bowl(pin);
        statuses.set(curIdx, status);

        if (status instanceof Strike || status instanceof Spare) {
            statuses.add(new Ready());
        }
        return this;
    }

    @Override
    public Status getStatus() {
        return null;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public Score getScore() {
        Score score = getFirstScore();
        for (int i = 1; i < statuses.size(); i++) {
            score = statuses.get(i).addScore(score);
        }
        return score;
    }

    private Score getFirstScore() {
        return getFirstStatus().getScore();
    }

    @Override
    public Score addScore(Score preScore) {
        Score score = preScore;
        int i = 0;
        while (!score.canCalculate() && i < statuses.size()) {
            score = statuses.get(i).addScore(score);
            i++;
        }
        return score;
    }

    @Override
    public Frame getNextFrame() {
        throw new RuntimeException("마지막 프레임입니다.");
    }

    @Override
    public boolean isFinished() {
        if (getFirstStatus() instanceof Spare || getFirstStatus() instanceof Strike) {
            return getRemainCountOfBowl() == 0;
        }
        return getFirstStatus().isFinished();
    }

    private int getRemainCountOfBowl() {
        Score score = getFirstScore();
        for (int i = 1; i < statuses.size(); i++) {
            score = statuses.get(i).addScore(score);
        }
        return score.getNextScoreCnt();
    }

    private Status getFirstStatus() {
        return statuses.get(0);
    }

    private List<Status> getNotReadyStatuses() {
        return statuses.stream()
                .filter(status -> !(status instanceof Ready))
                .collect(Collectors.toList());
    }

    public List<Status> getStatuses() {
        return statuses;
    }
}
