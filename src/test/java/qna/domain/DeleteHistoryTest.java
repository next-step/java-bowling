package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {
    @Test
    void create_질문_삭제_이력_생성() {
        assertThat(DeleteHistory.create(AnswerTest.JAVAJIGI_ANSWER)).isNotNull().isInstanceOf(DeleteHistory.class);
    }

    @Test
    void create_답변_삭제_이력_생성() {
        assertThat(DeleteHistory.create(QuestionTest.JAVAJIGI_QUESTION)).isNotNull().isInstanceOf(DeleteHistory.class);
    }
}
