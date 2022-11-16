package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(new ArticleInfo(UserTest.JAVAJIGI), new AnswerBody(QuestionTest.Q1, "Answers Contents1"));
    public static final Answer A2 = new Answer(new ArticleInfo(UserTest.SANJIGI), new AnswerBody(QuestionTest.Q1, "Answers Contents2"));

    @Test
    void delete() {
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }
}
