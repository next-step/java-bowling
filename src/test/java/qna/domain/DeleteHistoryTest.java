package qna.domain;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {
    public static DeleteHistory H1 = new DeleteHistory(ContentType.QUESTION, 100L, UserTest.JAVAJIGI, LocalDateTime.now());
    public static DeleteHistory H2 = new DeleteHistory(ContentType.ANSWER, 200L, UserTest.SANJIGI, LocalDateTime.now());

    @Test
    public void contentId() {
        assertThat(H1.contentId()).isEqualTo(100L);
    }
}
