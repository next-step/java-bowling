package qna.domain;

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

    protected DeleteHistory() {}

    public DeleteHistory(ContentType contentType, Long contentId, User deletedBy, LocalDateTime createDate) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createDate = createDate;
    }

    public DeleteHistory(Long id, ContentType contentType, Long contentId, User deletedBy, LocalDateTime createDate) {
        this.id = id;
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createDate = createDate;
    }

    public static DeleteHistory createQuestionHistory(Long questionId, User deletedBy) {
        return new DeleteHistory(ContentType.QUESTION, questionId, deletedBy, LocalDateTime.now());
    }

    public static DeleteHistory createQuestionHistory(Long id, Long questionId, User deletedBy) {
        return new DeleteHistory(id, ContentType.QUESTION, questionId, deletedBy, LocalDateTime.now());
    }

    public static DeleteHistory createAnswerHistory(Long answerId, User deletedBy) {
        return new DeleteHistory(ContentType.ANSWER, answerId, deletedBy, LocalDateTime.now());
    }

    public static DeleteHistory createAnswerHistory(Long id, Long answerId, User deletedBy) {
        return new DeleteHistory(id, ContentType.ANSWER, answerId, deletedBy, LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public Long getContentId() {
        return contentId;
    }

    public User getDeletedBy() {
        return deletedBy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DeleteHistory [id=" + id + ", contentType=" + contentType + ", contentId=" + contentId + ", deletedBy="
                + deletedBy + ", createDate=" + createDate + "]";
    }
}
