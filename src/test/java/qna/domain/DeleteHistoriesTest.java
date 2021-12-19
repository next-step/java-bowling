package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DeleteHistoriesTest {

    @Test
    void 객체생성() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents2"));

        DeleteHistories deleteHistories = DeleteHistories.from(question);
        assertThat(deleteHistories.size()).isEqualTo(3);
    }

}
