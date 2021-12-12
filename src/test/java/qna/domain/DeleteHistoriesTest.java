package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {

    @Test
    @DisplayName("get한 list size가 맞는지 테스트")
    void getSizeTest() {
        QuestionTest.Q1.addAnswer(AnswerTest.A1);
        QuestionTest.Q1.addAnswer(AnswerTest.A2);
        assertThat(new DeleteHistories(QuestionTest.Q1).get().size()).isEqualTo(3);
    }
}