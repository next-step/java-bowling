package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {
    private static final DeleteHistory D1 =
            new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI,
                    LocalDateTime.parse("2022-05-20T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME));

    @Test
    void add() {
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(D1);
        assertThat(deleteHistories.size()).isEqualTo(1);
    }
}
