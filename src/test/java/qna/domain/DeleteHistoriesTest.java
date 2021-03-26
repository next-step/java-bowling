package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {

    @Test
    void create() {
        Question questionWithOneAnswer = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(1L, UserTest.JAVAJIGI, questionWithOneAnswer, "Answers Contents1");
        LocalDateTime deleteDate = LocalDateTime.now();
        DeleteHistory expectedQuestionDeleteHistory = new DeleteHistory(ContentType.QUESTION, questionWithOneAnswer.getId(), questionWithOneAnswer.getWriter(), deleteDate);
        DeleteHistory expectedAnswerDeleteHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), deleteDate);

        DeleteHistories deleteHistories = new DeleteHistories(Arrays.asList(expectedQuestionDeleteHistory,expectedAnswerDeleteHistory));

        List<DeleteHistory> actualDeleteHistoryList = deleteHistories.getDeleteHistories();
        assertThat(actualDeleteHistoryList.size()).isEqualTo(2);
        assertThat(actualDeleteHistoryList).containsExactlyInAnyOrder(expectedQuestionDeleteHistory,expectedAnswerDeleteHistory);
    }

    @Test
    void add() {
        DeleteHistories deleteHistories = new DeleteHistories();
        DeleteHistory expectedQuestionDeleteHistory = new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, LocalDateTime.now());
        deleteHistories.add(expectedQuestionDeleteHistory);

        List<DeleteHistory> actual = deleteHistories.getDeleteHistories();
        assertThat(actual).containsExactly(expectedQuestionDeleteHistory);
    }
}
