package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DeletedStatusTest {

    @Test
    void isDeleted() {
        DeletedStatus deletedStatus = new DeletedStatus();
        assertThat(deletedStatus.isDeleted()).isFalse();
    }

    @Test
    void changeStatusToDeleted() {
        DeletedStatus deletedStatus = new DeletedStatus();
        deletedStatus.changeStatusToDeleted();
        assertThat(deletedStatus.isDeleted()).isTrue();
    }
}