package qna.domain;

public class Fixtures {
    public static User aUser1() {
        return new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    }

    public static User aUser2() {
        return new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    }

    public static Question aQuestion1() {
        return new Question("title1", "contents1").writeBy(aUser1());
    }

    public static Question aQuestion2() {
        return new Question("title2", "contents2").writeBy(aUser2());
    }

    public static Answer aAnswer1() {
        return new Answer(aUser1(), aQuestion1(), "Answers Contents1");
    }

    public static Answer aAnswer2() {
        return new Answer(aUser2(), aQuestion1(), "Answers Contents1");
    }
}
