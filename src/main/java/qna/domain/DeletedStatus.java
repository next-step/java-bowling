package qna.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DeletedStatus {

    @Column
    private boolean deleted;

    public DeletedStatus() {
        this.deleted = false;
    }

    public void changeStatusToDeleted() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
