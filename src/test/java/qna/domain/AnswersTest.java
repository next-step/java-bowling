package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswersTest {
    public static final Answer A1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(21L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answers actual;

    @BeforeEach
    public void setUp() {
        actual = new Answers();
    }

    @Test
    @DisplayName("Answer 추가")
    void add() {
        // when
        actual.add(A1);

        // then
        Answers expected = new Answers(Arrays.asList(A1));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Answer 삭제")
    void deleteAnswers() {
        // given
        actual.add(A1);

        // when
        actual.deleteAnswers(UserTest.JAVAJIGI);

        // then
        Answer deleted = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        deleted.setDeleted(true);

        Answers expected = new Answers(Arrays.asList(deleted));
        assertEquals(expected, actual);
    }
}
