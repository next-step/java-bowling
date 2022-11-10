package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test()
    @DisplayName("delete 검증")
    void delete() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        DeleteHistory deleteHistory = answer.delete();

        assertThat(answer).hasFieldOrPropertyWithValue("deleted", true);
        assertThat(deleteHistory)
                .hasFieldOrPropertyWithValue("contentId", answer.getId())
                .hasFieldOrPropertyWithValue("contentType", ContentType.ANSWER)
                .hasFieldOrPropertyWithValue("deletedBy", UserTest.JAVAJIGI);
    }
}
