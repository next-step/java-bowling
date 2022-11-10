package qna.domain;

import javax.persistence.Lob;

public abstract class AbstractContentsDeletableEntity extends AbstractEntity {
    @Lob
    private String contents;
    private boolean deleted = false;

    public AbstractContentsDeletableEntity() {
    }

    public AbstractContentsDeletableEntity(Long id, String contents) {
        super(id);
        this.contents = contents;
    }

    public AbstractContentsDeletableEntity(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public DeleteHistory delete() {
        this.deleted = true;
        return null;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
