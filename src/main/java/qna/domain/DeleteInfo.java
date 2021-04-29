package qna.domain;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class DeleteInfo {
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    private Long contentId;

    @ManyToOne
    private User deletedBy;

    public DeleteInfo() {
    }

    public DeleteInfo(AbstractEntity post, User deleteBy) {
        this(ContentType.check(post), post.getId(), deleteBy);
    }

    public DeleteInfo(ContentType contentType, Long contentId, User deletedBy) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteInfo that = (DeleteInfo) o;
        return contentType == that.contentType && Objects.equals(contentId, that.contentId) && Objects.equals(deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentType, contentId, deletedBy);
    }

    @Override
    public String toString() {
        return "DeleteInfo [contentType=" + contentType + ", contentId=" + contentId + ", deletedBy=" + deletedBy + "]";
    }
}
