package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = createDefaultTestAnswer();
    }

    @Test
    void createTest() {
        assertThat(answer).isNotNull();
    }

    @DisplayName("답변 작성자와 현재 로그인 사용자가 일치하는 경우 삭제 성공한다.")
    @Test
    void deleteByUser() throws CannotDeleteException {
        assertThat(answer.isDeleted()).isFalse();

        answer.deleteByUser(UserTest.JAVAJIGI);
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("답변 작성자와 현재 로그인 사용자가 일치하지 않는 경우 예외 발생한다.")
    @Test
    void deleteByUser2() {
        assertThat(answer.isDeleted()).isFalse();

        assertThatThrownBy(() -> {
            answer.deleteByUser(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    private static Answer createDefaultTestAnswer() {
        return new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "TestContents");
    }

}
