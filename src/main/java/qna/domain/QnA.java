package qna.domain;

import javax.persistence.Embeddable;

@Embeddable
public class QnA extends AbstractEntity {
    private boolean deleted = false;

    protected QnA() {
    }

    QnA(boolean deleted) {
        this.deleted = deleted;
    }

    public QnA(Long id) {
        super(id);
    }

    public void setDelete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return this.deleted;
    }
}
