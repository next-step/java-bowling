package qna.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> histories;

    public DeleteHistories() {
        this(new ArrayList<>());
    }

    private DeleteHistories(List<DeleteHistory> histories) {
        this.histories = histories;
    }

    public static DeleteHistories of(List<DeleteHistory> histories) {
        return new DeleteHistories(histories);
    }

    public void add(DeleteHistory deleteHistory) {
        histories.add(deleteHistory);
    }

    public void add(DeleteHistories deleteHistories) {
        this.histories.addAll(deleteHistories.histories);
    }

    public void save(CrudRepository<DeleteHistory, Long> repository) {
        repository.saveAll(histories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteHistories that = (DeleteHistories) o;

        return histories != null ? histories.equals(that.histories) : that.histories == null;
    }

    @Override
    public int hashCode() {
        return histories != null ? histories.hashCode() : 0;
    }
}
