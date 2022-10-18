package qna.domain;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

class AnswersTest {

    private static final Answers answers = new Answers();

    @Test
    @DisplayName("Answers 삭제 성공 | 작성자가 본인")
    void deleteSuccess() throws CannotDeleteException {
        answers.add(AnswerTest.A1);
        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);
        then(deleteHistories).hasSize(1);
        then(AnswerTest.A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("Answers 삭제 실패 | 작성자가 아님")
    void deleteFailByIsNotWriter() {
        answers.add(AnswerTest.A2);
        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}