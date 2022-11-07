package qna.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class ArticleEntity extends UpdatedEntity {

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

    protected String getContents() {
        return contents;
    }

}
