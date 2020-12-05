package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    @Test
    void deleteSelf() {
        User writer = new User();
        Question question = new Question();
        Answer answer1 = new Answer(writer, question, "Answers Contents1");
        Answer answer2 = new Answer(writer, question, "Answers Contents2");
        Answers answers = new Answers(Arrays.asList(answer1, answer2));
        LocalDateTime deleteDate = LocalDateTime.now();

        DeleteHistories deleteHistories = answers.deleteSelf(deleteDate);

        DeleteHistories deleteHistoriesExpected = new DeleteHistories(Arrays.asList(
                new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), deleteDate),
                new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), deleteDate)));
        assertThat(deleteHistories).isEqualTo(deleteHistoriesExpected);
    }
}
