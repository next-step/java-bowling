package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {
    @Test
    void Answer_객체로_생성() {
        assertThat(new DeleteHistory(AnswerTest.A1)).isEqualTo(new DeleteHistory(AnswerTest.A1));
    }

    @Test
    void Question_객체로_생성() {
        assertThat(new DeleteHistory(QuestionTest.Q1)).isEqualTo(new DeleteHistory(QuestionTest.Q1));
    }
}
