package bowling.domain.frame;

import bowling.bowlingexception.InvalidScoreCalculationException;
import bowling.domain.frame.status.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LastFrame extends Frame {

    private final List<Status> status;
    private int numPitch;

    public LastFrame() {
        super(10);
        numPitch = 0;
        status = new ArrayList<>();
        status.add(new Start());
    }

    @Override
    public Frame record(int downedPin) {
        if (getLatestStatus().isEnd()) {
            status.add(new Start());
        }

        numPitch += 1;
        Status newLatestStatus = getLatestStatus().record(downedPin);
        setLatestStatus(newLatestStatus);

        return null;
    }

    private Status getLatestStatus() {
        return status.get(status.size() - 1);
    }

    private void setLatestStatus(Status stat) {
        status.set(status.size() - 1, stat);
    }

    @Override
    public boolean isEnd() {
        Status initialStat = getInitialStatus();
        return initialStat instanceof Miss ||
                (initialStat instanceof Strike || initialStat instanceof Spare) && numPitch == 3;
    }

    private Status getInitialStatus() {
        return status.get(0);
    }

    @Override
    public String getDescriptionForm() {
        return status.stream()
                .map(Status::getDescription)
                .collect(Collectors.joining(" | "));
    }

    public int calculateScore() {
        if (!isEnd()) {
            throw new InvalidScoreCalculationException();
        }

        return 0;
    }
}
