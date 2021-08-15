package qna.domain.history.delete;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import qna.domain.type.ContentType;
import qna.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @EqualsAndHashCode.Exclude
    private LocalDateTime createDate = LocalDateTime.now();

    public DeleteHistory(final ContentType contentType, final Long contentId, final User deletedBy, final LocalDateTime createDate) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createDate = createDate;
    }

    public static DeleteHistory ofQuestion(final Long contentId, final User deletedBy) {
        return new DeleteHistory(ContentType.QUESTION, contentId, deletedBy, LocalDateTime.now());
    }

    public static DeleteHistory ofAnswer(final Long contentId, final User deletedBy) {
        return new DeleteHistory(ContentType.ANSWER, contentId, deletedBy, LocalDateTime.now());
    }
}
