package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

@DisplayName("답변들")
public class AnswersTest {

    private Answers answers;

    @Before
    public void setUp() {
        answers = new Answers();
    }

    @Test
    @DisplayName("답변 작성자 일치 확인")
    public void isOwnerTrue() throws Exception {
        //given
        User user = UserTest.JAVAJIGI;

        //when
        answers.add(AnswerTest.A2);
        answers.add(AnswerTest.A2);
        answers.add(AnswerTest.A2);
        answers.add(AnswerTest.A2);
        answers.add(AnswerTest.A1);
        boolean isOwner = answers.isOwner(user);

        //then
        assertThat(isOwner).isTrue();
    }


    @Test
    @DisplayName("답변 작성자 미일치 확인")
    public void isOwnerFalse() throws Exception {
        //given
        User user = UserTest.SANJIGI;

        //when
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A1);
        boolean isOwner = answers.isOwner(user);

        //then
        assertThat(isOwner).isFalse();
    }

}
