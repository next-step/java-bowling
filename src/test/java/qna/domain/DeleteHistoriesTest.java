package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {
    @Test
    void add() {
        DeleteHistories actualDeleteHistories = new DeleteHistories();
        Answer actualAnswer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.ANSWER, actualAnswer.getId(), actualAnswer.getWriter(), LocalDateTime.now());
        DeleteHistories expectedDeleteHistories = new DeleteHistories(Arrays.asList(deleteHistory));
        actualDeleteHistories.add(deleteHistory);
        assertThat(actualDeleteHistories).isEqualTo(expectedDeleteHistories);
    }
}
