package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {

    private final List<DeleteHistory> histories = new ArrayList<>();

    public void addHistory(DeleteHistory deleteHistory) {
        histories.add(deleteHistory);
    }

    public List<DeleteHistory> getHistories() {
        return histories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(histories, that.histories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(histories);
    }

    @Override
    public String toString() {
        return "DeleteHistories{" +
                "histories=" + histories +
                '}';
    }

}
