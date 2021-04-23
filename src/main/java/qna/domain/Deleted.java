package qna.domain;

import javax.persistence.Embeddable;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Embeddable
public class Deleted {

    private boolean delete = FALSE;

    public void delete() {
        this.delete = TRUE;
    }

    public void unDelete() {
        this.delete = FALSE;
    }

    public boolean isDelete() {
        return delete;
    }

    @Override
    public String toString() {
        return String.valueOf(delete);
    }
}
