package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 목록 중에 로그인 유저가 쓰지 않은 답변이 있으면 예외를 반환한다. ")
    @Test
    void validateCanDeleteWhenCannotDelete() {
        Answers answers = new Answers(Arrays.asList(A1, A2));
        User loginUser = UserTest.SANJIGI;

        assertThatThrownBy(() -> {
            answers.delete(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }
}