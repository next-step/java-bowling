package qna.domain;

public enum Status {
    UNDELETED(false),
    DELETED(true);

    private final boolean delete;

    Status(boolean delete) {
        this.delete = delete;
    }

    public boolean isDeleted() {
        return this.delete;
    }
}
