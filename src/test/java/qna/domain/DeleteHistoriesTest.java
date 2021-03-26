package qna.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {

    @Test
    void create() {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer1 = new Answer(1L, UserTest.JAVAJIGI, question, "Answers Contents1");
        Answer answer2 = new Answer(2L, UserTest.SANJIGI, question, "Answers Contents2");
        question.addAnswers(Arrays.asList(answer1, answer2));

        DeleteHistories deleteHistories = DeleteHistories.of(question);
        List<DeleteHistory> rawDeleteHistoryList = deleteHistories.getDeleteHistories();

        assertThat(rawDeleteHistoryList.size()).isEqualTo(3);
    }
}
