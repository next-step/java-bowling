package qna.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class ArticleEntity extends UpdatedEntity {

    @Lob
    private String contents;

    private boolean deleted = false;

    public ArticleEntity() {
    }

    public ArticleEntity(String contents) {
        this.contents = contents;
    }

    public ArticleEntity(Long id, String contents) {
        super(id);
        this.contents = contents;
    }

    public void setDeletedTrue() {
        deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (getId() ^ (getId() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "DeletableEntity{" +
                "deleted=" + deleted +
                '}';
    }
}
