package domain;

import domain.status.Status;

import java.util.ArrayList;
import java.util.List;

public class ResultManager {
    private List<Status> statuses;

    public ResultManager() {
        statuses = new ArrayList<>();
    }

    public void addResult(Status normalFrame) {
        statuses.add(normalFrame);
    }

    public List<Status> getStatuses() {
        return statuses;
    }

}
