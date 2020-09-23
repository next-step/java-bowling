package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @Test
    @DisplayName("답변을 삭제한다.")
    void softDeleteTest() throws CannotDeleteException {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
//        answers.add(AnswerTest.A2);

        List<DeleteHistory> deleteHistories = answers.softDelete(UserTest.JAVAJIGI);

        assertThat(deleteHistories).extracting("contentId")
                .containsExactly(AnswerTest.A1.getId());
    }

    @Test
    @DisplayName("답변 삭제 시 자신이 쓴 답변이 아니면 예외가 발생한다.")
    void checkIsOwnerTest() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThatThrownBy(() -> answers.softDelete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}