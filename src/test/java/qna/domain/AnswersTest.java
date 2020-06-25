package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    Answers answers;

    @BeforeEach
    void setUp() {
        Answer answer1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(22L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
        answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);
    }

    @DisplayName("로그인유저와 작성자가 다르면 false")
    @Test
    void failTest() {
        assertThat(answers.isEnableDeletedBy(UserTest.JAVAJIGI)).isFalse();
    }

}