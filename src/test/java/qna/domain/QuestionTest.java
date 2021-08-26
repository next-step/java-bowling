package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("Answers에 Owner가 존재하는지 테스트")
    @Test
    void isAnswersHaveOwner(){
        Q1.addAnswer2(AnswerTest.A1);
        Q1.addAnswer2(AnswerTest.A2);

        assertThat(Q1.isAnswerHaveOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @DisplayName("Answers에 Owner가 존재하지 않을 때 테스트")
    @Test
    void isNotAnswersHaveOwner(){
        Q1.addAnswer2(AnswerTest.A1);

        assertThat(Q1.isAnswerHaveOwner(UserTest.SANJIGI)).isFalse();
    }
}
