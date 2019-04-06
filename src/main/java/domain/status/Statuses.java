package domain.status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Statuses {
    List<Status> statuses = new ArrayList<>();

    public void add(Status status) {
        statuses.add(status);
    }

    public boolean isEmpty() {
        return statuses.isEmpty();
    }

    public Status getLastStatus() {
        return statuses.get(statuses.size()-1);
    }

    public Stream<Status> getStream() {
        return statuses.stream();
    }
}