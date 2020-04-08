package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(1, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete() {
        Q1.delete();
    }

    @Test
    void toDeleteHistory() {
        DeleteHistory deleteHistory = Q1.toDeleteHistory();

        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.QUESTION, 1l, UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    void getAnswers() {
        Q1.getAnswers();
    }
}
