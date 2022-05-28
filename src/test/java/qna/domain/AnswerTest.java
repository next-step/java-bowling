package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    public void setUp() {
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @DisplayName("Answer 삭제 테스트")
    @Test
    void deleteAnswer() {
        Answers answers = new Answers(Arrays.asList(answer));
        answers.delete(UserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("Answer 삭제 CannotDeleteException 테스트")
    @Test
    void deleteAnswerCannotDeleteException() {
        Answers answers = new Answers(Arrays.asList(answer));
        assertThatThrownBy(() -> {
            answers.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}