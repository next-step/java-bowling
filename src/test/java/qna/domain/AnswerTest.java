package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    void setup() {
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }


    @Test
    @DisplayName("삭제 명령을 수행한다.")
    void deleteWithValidUser() {
        answer.delete();
        assertThat(answer.isDeleted()).isTrue();
    }
}
