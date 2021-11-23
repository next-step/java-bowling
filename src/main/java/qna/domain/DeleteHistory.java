package qna.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class DeleteHistory {
    @EmbeddedId
    private HistoryId historyId;

    @Embedded
    private HistoryContent historyContent;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_deletehistory_to_user"))
    private User deletedBy;

    private LocalDateTime createDate = LocalDateTime.now();

    protected DeleteHistory() {
    }

    private DeleteHistory(HistoryContent historyContent, User deletedBy, LocalDateTime createDate) {
        this.historyContent = historyContent;
        this.deletedBy = deletedBy;
        this.createDate = createDate;
    }

    public static DeleteHistory create(HistoryContent historyContent, User deletedBy,
                                       LocalDateTime createDate) {
        return new DeleteHistory(historyContent, deletedBy, createDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(historyId, that.historyId) && Objects.equals(historyContent, that.historyContent)
                && Objects.equals(deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyId, historyContent, deletedBy);
    }

    @Override
    public String toString() {
        return "DeleteHistory{historyId=" + historyId + ", historyContent=" + historyContent + ", " +
                "deletedBy=" + deletedBy + ", createDate=" + createDate + '}';
    }
}
