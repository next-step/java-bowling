package qna.domain;

import org.junit.jupiter.api.BeforeEach;

public class AnswerTest {
    public Answer A1;
    public Answer A2;

    @BeforeEach
    void init() {
         Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
         Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
         A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
         A2 = new Answer(UserTest.SANJIGI, Q2, "Answers Contents2");
    }
}
