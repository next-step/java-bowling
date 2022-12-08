package qna.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by seungwoo.song on 2022-11-08
 */
public class DeleteHistories implements Iterable<DeleteHistory> {

    private final List<DeleteHistory> values;

    @Override
    public Iterator<DeleteHistory> iterator() {
        return values.iterator();
    }

    public DeleteHistories(List<DeleteHistory> values) {
        this.values = values;
    }

    public DeleteHistory get(int index) {
        return values.get(index);
    }

    public int size() {
        return values.size();
    }

    public boolean add(DeleteHistory deleteHistory) {
        return values.add(deleteHistory);
    }

    // ============================================================================


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "DeleteHistories{" +
                "values=" + values +
                '}';
    }
}
