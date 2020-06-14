package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @DisplayName("답변자가 자신이면 true 반환")
    @Test
    public void isOwner() {
        User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");

        assertThat(A1.isOwner(JAVAJIGI)).isTrue();
    }
}
