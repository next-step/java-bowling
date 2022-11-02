package step1.qna.domain;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step1.qna.CannotDeleteException;

class AnswersTest {

    @DisplayName("작성자와 모두 같은 답변자일 경우 삭제 가능")
    @Test
    void answersDelete() {
        Answers answers = Answers.from(Arrays.asList(AnswerTest.A1, AnswerTest.A1, AnswerTest.A1));
        answers.deleteAll(UserTest.JAVAJIGI);
        int countOfDeletedAnswer = (int) answers.getAnswers().stream()
            .filter(answer -> answer.isDeleted())
            .count();
        Assertions.assertThat(countOfDeletedAnswer).isEqualTo(3);
    }

    @DisplayName("작성자와 다른 답변자가 한 명이라도 있을 경우 예외 처리")
    @Test
    void answers_delete() {
        Answers answers = Answers.from(Arrays.asList(AnswerTest.A1, AnswerTest.A2, AnswerTest.A2));
        Assertions.assertThatThrownBy(() -> answers.deleteAll(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

}
