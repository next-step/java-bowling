package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("로그인 유저가 쓰지 않았다면 예외를 반환한다. ")
    @Test
    void validateCanDeleteWhenLoginUserIsNotWriter() {
        User loginUser = UserTest.SANJIGI;

        assertThatThrownBy(() -> {
            A1.delete(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }
}