package qna.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    private DeleteHistory() {
    }

    public DeleteHistory(Answer answer, User deletedBy) {
        contentType = ContentType.ANSWER;
        contentId = answer.getId();
        this.deletedBy = deletedBy;
    }

    public DeleteHistory(Question question, User deletedBy) {
        contentType = ContentType.QUESTION;
        contentId = question.getId();
        this.deletedBy = deletedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
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
