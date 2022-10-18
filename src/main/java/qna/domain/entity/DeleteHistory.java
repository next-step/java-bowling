package qna.domain.entity;

import qna.exception.CannotDeleteException;

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

    private LocalDateTime createDate;

    private DeleteHistory(ContentType contentType, Long contentId, User deletedBy, LocalDateTime createDate) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createDate = createDate;
    }

    public DeleteHistory(Answer answer, User deletedBy){
        this(ContentType.ANSWER, answer.getId(), deletedBy, LocalDateTime.now());

        if (!answer.isDeleted()) {
            throw new CannotDeleteException("삭제된 답변만 이력으로 만들 수 있습니다.");
        }
    }

    public DeleteHistory(Question question, User deletedBy){
        this(ContentType.QUESTION, question.getId(), deletedBy, LocalDateTime.now());

        if (!question.isDeleted()) {
            throw new CannotDeleteException("삭제된 답변만 이력으로 만들 수 있습니다.");
        }
    }

    public DeleteHistory() {

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

}
