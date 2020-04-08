package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.deletehistory.DeleteHistory;
import qna.domain.qna.answer.Answer;
import qna.domain.qna.question.Question;
import qna.domain.user.User;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    private Question question;
    private Answer answer;

    @BeforeEach
    void setUp() {
        question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
    }

    @DisplayName("로그인 유저가 쓰지 않았다면 예외를 반환한다. ")
    @Test
    void validateCanDeleteWhenLoginUserIsNotWriter() {
        User loginUser = UserTest.SANJIGI;

        assertThatThrownBy(() -> {
            answer.delete(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("로그인 유저가 답변 작성자라면 아무런 예외를 발생하지 않는다.")
    @Test
    void validateCanDeleteWhenLoginUserIsWriter() {
        User loginUSer = UserTest.JAVAJIGI;

        assertThatCode(() -> {
            answer.delete(loginUSer);
        }).doesNotThrowAnyException();
    }

    @DisplayName("로그인 유저가 답변 작성자라면 답변의 deleted 상태는 true로 바뀐다.")
    @Test
    void changeDeletedStateWhenCanDelete() throws CannotDeleteException {
        //given
        User loginUser = UserTest.JAVAJIGI;

        //when
        answer.delete(loginUser);

        //then
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("Answer가 삭제되면 DeleteHistory 객체를 반환한다.")
    @Test
    void returnDeleteHistoryWhenDelete() throws CannotDeleteException {
        //given
        User loginUser = UserTest.JAVAJIGI;

        //when
        DeleteHistory deleteHistory = answer.delete(loginUser);

        //then
        assertThat(deleteHistory.getDeletedBy()).isEqualTo(loginUser);
    }
}