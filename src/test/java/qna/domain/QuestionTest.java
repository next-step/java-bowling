package qna.domain;

public class QuestionTest {
    public static final Question Q1 = Question.create("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = Question.create("title2", "contents2").writeBy(UserTest.SANJIGI);
}
