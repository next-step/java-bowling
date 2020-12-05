package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;

class AnswersTest {

    private static final List<Answer> answerList = Arrays.asList(A1, A2);

    @Test
    void deleteSelf() {
        Answers answers = new Answers(answerList);
        LocalDateTime deleteDate = LocalDateTime.now();

        DeleteHistories deleteHistories = answers.deleteSelf(deleteDate);

        DeleteHistories deleteHistoriesExpected = new DeleteHistories(Arrays.asList(
                new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), deleteDate),
                new DeleteHistory(ContentType.ANSWER, A2.getId(), A2.getWriter(), deleteDate)));
        assertThat(deleteHistories).isEqualTo(deleteHistoriesExpected);
    }
}
