package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DeleteHistoriesTest {

    @Test
    @DisplayName("Question 삭제 기록 추가")
    void add_question() {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        LocalDateTime now = LocalDateTime.now();

        // when
        DeleteHistories actual = new DeleteHistories();
        actual.add(question);

        // then
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), now);
        DeleteHistories expected = new DeleteHistories(Arrays.asList(deleteHistory));

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Answers 삭제 기록 추가")
    void add_answers() {
        // given
        Answer answerA = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answerB = new Answer(21L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        LocalDateTime now = LocalDateTime.now();

        // when
        DeleteHistories actual = new DeleteHistories();
        actual.add(Arrays.asList(answerA, answerB));

        // then
        DeleteHistory deleteHistoryA = new DeleteHistory(ContentType.ANSWER, answerA.getId(), answerA.getWriter(), now);
        DeleteHistory deleteHistoryB = new DeleteHistory(ContentType.ANSWER, answerB.getId(), answerB.getWriter(), now);
        DeleteHistories expected = new DeleteHistories(Arrays.asList(deleteHistoryA, deleteHistoryB));

        assertEquals(actual, expected);
    }
}
