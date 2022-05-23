package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    public static Answer a1() {
        return new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    public static Answer a2() {
        return new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    void delete는_답변자와_사용자가_같을_경우_삭제가_가능하다() {
        Answer answer = a1();

        answer.delete(UserTest.JAVAJIGI);

        assertTrue(answer.isDeleted());
    }

    @Test
    void delete는_답변자와_사용자가_다를_경우_예외를_발생시킨다() {
        Answer answer = a1();

        assertThatThrownBy(() -> {
            answer.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
