package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    @DisplayName("생성 테스트")
    @Test
    void create(){
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        assertThat(answers).isEqualTo(Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A2)));
    }

    @DisplayName("add 메소드 테스트")
    @Test
    void add(){
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        Answers addAnswers = new Answers();
        addAnswers.add(AnswerTest.A1);
        addAnswers.add(AnswerTest.A2);

        assertThat(answers).isEqualTo(addAnswers);
    }

    @DisplayName("Owner 가지고 있는지 테스트")
    @Test
    void isOwnerTest(){
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A2));


        assertThat(answers.isOwner(UserTest.JAVAJIGI)).isTrue();
        assertThat(answers.isOwner(UserTest.SANJIGI)).isTrue();
    }

    @DisplayName("Owner 가지고 있지 않을 때 테스트")
    @Test
    void isNotOwnerTest(){
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1));

        assertThat(answers.isOwner(UserTest.SANJIGI)).isFalse();
    }

    @DisplayName("setDeleted 테스트")
    @Test
    void setDeleted(){
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        answers.setDeleted(true);

        assertThat(AnswerTest.A1.isDeleted()).isTrue();
        assertThat(AnswerTest.A2.isDeleted()).isTrue();
    }
}
