package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    @DisplayName("로그인 유저가 쓰지 않았다면 예외를 반환한다. ")
    @Test
    void validateCanDeleteWhenLoginUserIsNotWriter() {
        User loginUser = UserTest.SANJIGI;

        assertThatThrownBy(() -> {
            A1.delete(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("로그인 유저가 답변 작성자라면 아무런 예외를 발생하지 않는다.")
    @Test
    void validateCanDeleteWhenLoginUserIsWriter(){
        User loginUSer = UserTest.JAVAJIGI;

        assertThatCode(() -> {
            A1.delete(loginUSer);
        }).doesNotThrowAnyException();
    }

    @DisplayName("로그인 유저가 답변 작성자라면 답변의 deleted 상태는 true로 바뀐다.")
    @Test
    void changeDeletedStateWhenCanDelete() throws CannotDeleteException {
        //given
        User loginUser = UserTest.JAVAJIGI;

        //when
        A1.delete(loginUser);

        //then
        assertThat(A1.isDeleted()).isTrue();
    }
}