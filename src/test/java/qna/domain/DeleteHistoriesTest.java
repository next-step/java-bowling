package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class DeleteHistoriesTest {

    @Test
    void create() {
        DeleteHistory deleteHistory = DeleteHistory.of(ContentType.ANSWER, 1L, UserTest.JAVAJIGI, LocalDateTime.now());
        assertThat(DeleteHistories.from(deleteHistory)).isNotNull();
    }
}