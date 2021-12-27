package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {
    @Test
    @DisplayName("Question이 Answer 보다 앞으로 오는지 테스트")
    void prepend() throws CannotDeleteException {
        //given
        Question question = QuestionTest.Q1;
        question.addAnswer(AnswerTest.A1);

        //when
        DeleteHistory questionDeleteHistory = question.toDeleteHistory();
        DeleteHistories deleteHistories = question.deleteByUser(UserTest.JAVAJIGI);

        //then
        assertThat(deleteHistories.getDeleteHistories().get(0)).isEqualTo(questionDeleteHistory);
    }
}