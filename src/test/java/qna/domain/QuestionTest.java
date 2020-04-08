package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    @DisplayName("로그인 유저가 질문 작성자이면 예외가 발생하지 않음")
    @Test
    void validateCanDeleteWhenLoginUserIsWriter() {
        User loginUser = UserTest.JAVAJIGI;

        assertThatCode(() -> {
            Q1.delete(loginUser);
        }).doesNotThrowAnyException();
    }

    @DisplayName("로그인 유저가 질문 작성자가 아니면 예외 발생")
    @Test
    void validateCanDeleteWhenLoginUserIsNotWriter() {
        User loginUser = UserTest.SANJIGI;

        assertThatThrownBy(() -> {
            Q1.delete(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("로그인 유저가 질문 작성자라면 deleted 상태가 true로 바뀐다.")
    @Test
    void changeDeletedStateWhenLoginUserIsWriter() throws CannotDeleteException {
        //given
        User loginUser = UserTest.JAVAJIGI;

        //when
        Q1.delete(loginUser);

        //then
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("답변과 질문을 모두 로그인유저가 작성한 경우, 질문과 답변의 deleted가 모두 true로 바뀐다.")
    @Test
    void changeDeletedStateQuestionAndAnswersWhenLoginUserIsWriter() throws CannotDeleteException {
        //given
        User loginUser = UserTest.JAVAJIGI;

        //when
        Q1.delete(loginUser);

        //then
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isTrue();
    }
}