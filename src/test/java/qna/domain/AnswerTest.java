package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;
import qna.domain.entity.Answer;
import qna.domain.entity.DeleteHistory;
import qna.domain.entity.Question;
import qna.domain.entity.User;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 생성")
    public void answerCreate(){
        Question q1 = QuestionTest.Q1;
        User user = UserTest.JAVAJIGI;
        String contents = "Answers Contents1";

        Answer answer = new Answer(user, q1, contents);

        assertThat(answer.equals(new Answer(user, q1, contents))).isTrue();
    }

    @Test
    @DisplayName("답변 작성자 확인")
    public void answerUserCheck(){
        assertThatThrownBy(
                () -> new Answer(null, QuestionTest.Q1, "Answers Contents3")
        ).isInstanceOf(UnAuthorizedException.class).hasMessageContaining("답변은 로그인 하신 후에 작성 가능합니다.");
    }

    @Test
    @DisplayName("답변할 질문 확인")
    public void answerQuestionCheck(){
        assertThatThrownBy(
                () -> new Answer(UserTest.JAVAJIGI, null, "Answers Contents3")
        ).isInstanceOf(NotFoundException.class).hasMessageContaining("답변을 할 질문이 없습니다.");
    }

    @Test
    @DisplayName("답변 삭제")
    public void answerDelete() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변 삭제 이력")
    public void answerDeleteHistory() throws CannotDeleteException {
        DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistory.equals(DeleteHistory.answerHistory(AnswerTest.A1.getId(), AnswerTest.A1.getWriter()))).isTrue();
    }

    @Test
    @DisplayName("답변 삭제 권한 없음")
    public void answerDeleteNoAuth() {
        assertThatThrownBy(
                () -> A1.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class).hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
