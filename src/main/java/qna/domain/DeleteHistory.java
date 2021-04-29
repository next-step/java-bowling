package qna.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class DeleteHistory {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AssociationOverride(name = "deletedBy", joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "fk_deletehistory_to_user")))
    private DeleteInfo deleteInfo;

    private LocalDateTime createDate = LocalDateTime.now();

    public DeleteHistory() {
    }

    public DeleteHistory(ContentType contentType, Long contentId, User deletedBy, LocalDateTime createDate) {
        this.deleteInfo = new DeleteInfo(contentType, contentId, deletedBy);
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(deleteInfo, that.deleteInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deleteInfo, createDate);
    }


    @Override
    public String toString() {
        return "DeleteHistory [id=" + id + ", deleteInfo=" + deleteInfo + ", createDate=" + createDate + "]";
    }
}
