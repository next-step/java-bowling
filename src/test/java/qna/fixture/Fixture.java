package qna.fixture;

import qna.domain.Answer;
import qna.domain.Answers;
import qna.domain.Question;
import qna.domain.User;

import java.util.Arrays;

public class Fixture {

    private final User javajigi;
    private final User sanjigi;

    private final Question question1;
    private final Question question2;

    private final Answer answer1;
    private final Answer answer2;

    private final Answers answers;

    private Fixture() {
        javajigi = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        sanjigi = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

        question1 = new Question("title1", "contents1").writeBy(javajigi);
        answer1 = new Answer(javajigi, question1, "Answers Contents1");
        answer2 = new Answer(sanjigi, question1, "Answers Contents2");
        answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);

        question2 = new Question("title2", "contents2").writeBy(sanjigi);
    }

    public static Fixture of() {
        return new Fixture();
    }

    public User getJavajigi() {
        return javajigi;
    }

    public User getSanjigi() {
        return sanjigi;
    }

    public Question getQuestion1() {
        return question1;
    }

    public Question getQuestion2() {
        return question2;
    }

    public Answer getAnswer1() {
        return answer1;
    }

    public Answer getAnswer2() {
        return answer2;
    }

    public Answers getAnswers() {
        return answers;
    }
}
