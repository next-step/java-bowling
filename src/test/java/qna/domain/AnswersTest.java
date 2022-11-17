package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private static final Answers answers = new Answers();

    @Test
    @DisplayName("삭제 성공")
    void deleteSuccess() throws CannotDeleteException {
        // given
        answers.add(AnswerTest.A1);

        // when
        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);

        // then
        assertThat(deleteHistories).hasSize(1);
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제 실패 - 작성자 다른 경우")
    void deleteFail() {
        // given
        answers.add(AnswerTest.A1);

        // expected
        assertThatThrownBy(() -> answers.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
