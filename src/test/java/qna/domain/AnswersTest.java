package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @Test
    @DisplayName("본인의 질문에 본인이 작성한 답변인 것만 있을 때, 삭제 성공하고 질문 삭제 히스토리에 추가된다.")
    void deleteAnswersSuccessTest() throws CannotDeleteException {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        List<DeleteHistory> deleteHistories = answers.deleteAll(UserTest.JAVAJIGI);
        assertThat(deleteHistories).hasSize(1);
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("본인의 질문에 본인 이외의 답변 작성자가 있을시, 삭제 실패와 에러발생 한다.")
    void deleteAnswersFailByIsNotWriter() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A2);
        assertThatThrownBy(() -> answers.deleteAll(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
