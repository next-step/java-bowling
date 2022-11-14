package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {
    @Test
    void 답변_히스토리_생성() {
        assertThat(DeleteHistory.ofAnswer(0L, UserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, 0L, UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    void 질문_히스토리_생성() {
        assertThat(DeleteHistory.ofQuestion(0L, UserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistory(ContentType.QUESTION, 0L, UserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
