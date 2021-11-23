package qna.domain;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HistoryId implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryId historyId = (HistoryId) o;
        return Objects.equals(id, historyId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
