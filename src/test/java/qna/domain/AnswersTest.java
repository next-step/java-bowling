package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswersTest {
    private Answers answers;
    private Answer answerOfJavajigi;
    private Answer answerOfSanjigi;

    @BeforeEach
    public void setUp() {
        answers = new Answers();
        answerOfJavajigi = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answerOfSanjigi = new Answer(21L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    @DisplayName("Answer 추가")
    void add() {
        // when
        Answers actual = answers.add(answerOfJavajigi);

        // then
        Answers expected = new Answers(Arrays.asList(answerOfJavajigi));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("불변 검사1 - add 반환값")
    void add_immuatable() {
        // when
        Answers actual = answers.add(answerOfJavajigi);
        assertTrue(actual != answers);
    }

    @Test
    @DisplayName("Answer 삭제")
    void deleteAnswers() {
        // given
        Answers addedAnswers = answers.add(answerOfJavajigi);

        // when
        Answers actual = addedAnswers.deleteAnswers(UserTest.JAVAJIGI);

        // then
        Answer deleted = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        deleted.deleteAnswer(UserTest.JAVAJIGI);

        Answers expected = new Answers(Arrays.asList(deleted));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("불변 검사2 - deleteAnswers 반환값")
    void deleteAnswers_immuatable() {
        // given
        Answers addedAnswers = answers.add(answerOfJavajigi);

        // when
        Answers actual = addedAnswers.deleteAnswers(UserTest.JAVAJIGI);
        assertTrue(actual != addedAnswers);
        assertTrue(actual != answers);
    }
}
