package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void deletable() {
        Assertions.assertThat(Q1.deletable(UserTest.JAVAJIGI)).isTrue();
        System.out.println("Q1.deletable(UserTest.JAVAJIGI) = " + Q1.deletable(UserTest.JAVAJIGI));

        Q1.addAnswer(AnswerTest.A1);
        System.out.println("Q1.deletable(UserTest.JAVAJIGI) = " + Q1.deletable(UserTest.JAVAJIGI));
        Assertions.assertThat(Q1.deletable(UserTest.JAVAJIGI)).isTrue();

        Q1.addAnswer(AnswerTest.A2);
        System.out.println("Q1.deletable(UserTest.JAVAJIGI) = " + Q1.deletable(UserTest.JAVAJIGI));
        Assertions.assertThat(Q1.deletable(UserTest.JAVAJIGI)).isFalse();
    }
}
