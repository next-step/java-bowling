package qna.domain.question;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.UserTest;
import qna.domain.history.ContentType;
import qna.domain.history.DeleteHistory;

public class AnswerTest {
    public static final Answer JAVAJIGI = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer SANJIGI = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    @DisplayName("답변을 삭제하면 deleted false가 된다.")
    @Test
    void delete_then_deleted_is_true() throws CannotDeleteException {
        answer.deleteBy(UserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("답변을 삭제하면 히스토리를 반환한다.")
    @Test
    void delete_question_then_history() throws CannotDeleteException {
        DeleteHistory deleteHistory = answer.deleteBy(UserTest.JAVAJIGI);

        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같지 않을 경우 삭제를 하면 예외가 발생한다.")
    @Test
    void delete_then_throw_exception_if_other_user() {
        assertThatThrownBy(()->answer.deleteBy(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
