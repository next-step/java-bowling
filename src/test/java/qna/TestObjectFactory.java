package qna;

import qna.domain.*;

public class TestObjectFactory {

    public static User createUser1() {
        return new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    }

    public static User createUser2() {
        return new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    }

    public static Question createQuestion1(User user) {
        return new Question(1L, "title1", "contents1").writeBy(user);
    }

    public static Question createQuestion2(User user) {
        return new Question(2L, "title2", "contents2").writeBy(user);
    }

    public static Answer createAnswer1(User user, Question question) {
        return new Answer(1L, user, question, "Answers Contents1");
    }

    public static Answer createAnswer2(User user, Question question) {
        return new Answer(2L, user, question, "Answers Contents2");
    }
}
