package bowling.domain.frame;

import bowling.domain.frame.status.Start;
import bowling.domain.frame.status.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LastFrame extends Frame {

    private final List<Status> status;
    private final List<Integer> downedPins;

    public LastFrame() {
        this.downedPins = new ArrayList<>();
        status = new ArrayList<>();
        status.add(new Start());
    }

    @Override
    public void record(int downedPin) {
        downedPins.add(downedPin);

        if (getLatestStatus().isEnd()) {
            status.add(new Start());
        }

        Status newLatestStatus = getLatestStatus().record(downedPin);
        setLatestStatus(newLatestStatus);
    }

    private Status getLatestStatus() {
        return status.get(status.size() - 1);
    }

    private void setLatestStatus(Status stat) {
        status.set(status.size() - 1, stat);
    }

    @Override
    public boolean isEnd() {
        return (downedPins.size() == 3 && downedPins.get(0) == 10)
                || (downedPins.size() == 2 && downedPins.get(0) != 10 && downedPins.get(0) + downedPins.get(1) != 10)
                || (downedPins.size() == 3 && (downedPins.get(0) + downedPins.get(1)) == 10);
    }

    @Override
    public String getDescriptionForm() {
        return status.stream()
                .map(Status::getDescription)
                .collect(Collectors.joining(" | "));
    }
}
