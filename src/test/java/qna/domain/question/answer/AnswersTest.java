package qna.domain.question.answer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.deleteHistory.DeleteHistory;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static qna.domain.deleteHistory.ContentType.ANSWER;
import static qna.domain.question.answer.AnswerTest.A1;
import static qna.domain.user.UserTest.JAVAJIGI;
import static qna.domain.user.UserTest.SANJIGI;

class AnswersTest {

    @Test
    @DisplayName("Answers 객체를 생성한다")
    void shouldCreate() {
        Answers answers = answers();
        assertThat(answers).isEqualTo(answers());
    }

    @Test
    @DisplayName("답변의 모든 작성자가 로그인 한 유저와 일치하지 않으면 예외를 던진다")
    void shouldThrowWhenNotEqualLoginUser() {
        Answers answers = answers();
        assertThatThrownBy(() -> answers.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변의 모든 작성자가 로그인 한 유저와 일치하면 삭제한다")
    void shouldDelete() throws CannotDeleteException {
        Answers answers = answers();
        assertThat(answers.delete(JAVAJIGI)).isEqualTo(singletonList(DeleteHistory.of(ANSWER, A1)));
    }

    private Answers answers() {
        return Answers.from(singletonList(A1));
    }
}