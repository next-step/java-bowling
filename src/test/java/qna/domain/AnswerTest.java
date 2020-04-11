package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.TestConstant.A1;
import static qna.TestConstant.JAVAJIGI;

public class AnswerTest {
    @Test
    void delete() {
        DeleteHistory deleteHistory = A1.delete();

        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, 1l, JAVAJIGI, LocalDateTime.now()));
    }
}
