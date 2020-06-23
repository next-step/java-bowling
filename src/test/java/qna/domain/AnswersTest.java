package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.QuestionTest.DELETE_HISTORIES;

class AnswersTest {
    @Test
    @DisplayName("질문자와 답변자가 같은 경우 삭제")
    void delete_ok() throws CannotDeleteException {
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A1));
        answers.delete(UserTest.JAVAJIGI, DELETE_HISTORIES);
    }

    @Test
    @DisplayName("질문자와 다른 답변자가 있을 경우 실패")
    void delete_fail() {
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
        assertThatThrownBy(()-> answers.delete(UserTest.JAVAJIGI, DELETE_HISTORIES))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
