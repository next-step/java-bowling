package qna.domain.deleteHistory;

import qna.domain.question.Question;
import qna.domain.question.answer.Answer;
import qna.domain.user.User;

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

    private <T> DeleteHistory(ContentType contentType, Question question) {
        this.contentType = contentType;
        this.contentId = question.getId();
        this.deletedBy = question.getWriter();
    }

    private <T> DeleteHistory(ContentType contentType, Answer answer) {
        this.contentType = contentType;
        this.contentId = answer.getId();
        this.deletedBy = answer.getWriter();
    }

    public static DeleteHistory of(ContentType contentType, Question question) {
        return new DeleteHistory(contentType, question);
    }

    public static DeleteHistory of(ContentType contentType, Answer answer) {
        return new DeleteHistory(contentType, answer);
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
