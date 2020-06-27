package qna.domain;

import org.junit.jupiter.api.BeforeEach;

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


}