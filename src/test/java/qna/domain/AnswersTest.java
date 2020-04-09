package qna.domain;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class AnswersTest {

    private Answer answer;

    @Before
    public void setUp() throws Exception {
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @DisplayName("생성자")
    @Test
    public void constructor() throws Exception {
        Answers answers = new Answers(Arrays.asList(new Answer(), new Answer()));
    }
}
