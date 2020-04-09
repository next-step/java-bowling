package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.TestConstant.A1;
import static qna.TestConstant.JAVAJIGI;

public class AnswerTest {
    @Test
    void delete() {
        A1.delete();
    }

    @Test
    void toDeleteHistory() {
        DeleteHistory deleteHistory = A1.toDeleteHistory();

        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, 1l, JAVAJIGI, LocalDateTime.now()));
    }
}
