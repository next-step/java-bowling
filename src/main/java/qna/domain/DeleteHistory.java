package qna.domain;

import qna.builder.Buildable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class DeleteHistory {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    private Long contentId;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_deletehistory_to_user"))
    private User deletedBy;

    private LocalDateTime createDate = LocalDateTime.now();

    public DeleteHistory() {
    }

    private DeleteHistory(Builder builder) {
        this.contentType = builder.contentType;
        this.contentId = builder.contentId;
        this.deletedBy = builder.deletedBy;
        this.createDate = builder.createDate;
    }

    public static class Builder implements Buildable {
        private final ContentType contentType;
        private final Long contentId;
        private User deletedBy;
        private LocalDateTime createDate;

        public Builder(final ContentType contentType, final Long contentId) {
            this.contentType = contentType;
            this.contentId = contentId;
        }

        public Builder deletedBy(final User deletedBy) {
            this.deletedBy = deletedBy;
            return this;
        }

        public Builder createDate(final LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public DeleteHistory build() {
            return new DeleteHistory(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id) &&
                contentType == that.contentType &&
                Objects.equals(contentId, that.contentId) &&
                Objects.equals(deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contentType, contentId, deletedBy);
    }

    @Override
    public String toString() {
        return "DeleteHistory [id=" + id + ", contentType=" + contentType + ", contentId=" + contentId + ", deletedBy="
                + deletedBy + ", createDate=" + createDate + "]";
    }
}
