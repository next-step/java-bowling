package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void deleteTest() {
        assertThat(A1.delete().isDeleted()).isTrue();
    }

    @Test
    void answersCanDeleteTest() {
        Answers answers = Answers.of(Arrays.asList(A1, A2), A1.getWriter());
        assertThatThrownBy(() ->answers.canDelete())
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void canDeleteTest() {
        assertThatThrownBy(
                () -> A1.canDelete(A2.getWriter()))
                .isInstanceOf(CannotDeleteException.class);

    }
}


