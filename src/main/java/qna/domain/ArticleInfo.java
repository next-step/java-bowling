package qna.domain;

import qna.UnAuthorizedException;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Embeddable
public class ArticleInfo {

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer = new User();

    private boolean deleted = false;

    public ArticleInfo() {
    }

    public ArticleInfo(User writer) {
        if (writer == null) {
            throw new UnAuthorizedException();
        }
        this.writer = writer;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(User user) {
        return this.writer.equals(user);
    }

    public void writeBy(User loginUser) {
        this.writer = loginUser;
    }

    public void delete() {
        this.deleted = true;
    }

    public User getWriter() {
        return writer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleInfo that = (ArticleInfo) o;
        return deleted == that.deleted && Objects.equals(writer, that.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(writer, deleted);
    }
}
