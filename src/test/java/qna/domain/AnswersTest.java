package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents3");
    public static final Answer A4 = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents4");

    @DisplayName("답변 목록 중에 로그인 유저가 쓰지 않은 답변이 있으면 예외를 반환한다. ")
    @Test
    void validateCanDeleteWhenCannotDelete() {
        Answers answers = new Answers(Arrays.asList(A1, A2));
        User loginUser = UserTest.SANJIGI;

        assertThatThrownBy(() -> {
            answers.delete(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변 목록이 모두 로그인 유저가 쓴 답변이라면 deleted가 true로 바뀐다.")
    @Test
    void changeDeletedStateWhenLoginUserIsWriter() throws CannotDeleteException {
        //given
        Answers answers = new Answers(Arrays.asList(A3, A4));
        User loginUser = UserTest.SANJIGI;

        //when
        answers.delete(loginUser);

        //then
        assertThat(A3.isDeleted()).isTrue();
        assertThat(A4.isDeleted()).isTrue();
    }
}