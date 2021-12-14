package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DeleteHistoriesTest {

    @Test
    void 객체생성() {
        Question q1 = QuestionTest.Q1;
        q1.addAnswer(AnswerTest.A1);
        q1.addAnswer(AnswerTest.A2);

        DeleteHistories deleteHistories = DeleteHistories.from(q1);
        assertThat(deleteHistories.size()).isEqualTo(3);
    }

}
