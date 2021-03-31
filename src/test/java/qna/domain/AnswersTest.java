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
    @DisplayName("모든 답변 작성자 일치")
    public void isAllOwnerTrue() throws Exception {
        //given
        User user = UserTest.JAVAJIGI;

        //when
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A1);
        boolean isOwner = answers.isAllOwner(user);

        //then
        assertThat(isOwner).isTrue();
    }


    @Test
    @DisplayName("모든 답변 작성자 미일치")
    public void isAllOwnerFalse() throws Exception {
        //given
        User user = UserTest.SANJIGI;

        //when
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);
        boolean isOwner = answers.isAllOwner(user);

        //then
        assertThat(isOwner).isFalse();
    }

    @Test
    @DisplayName("삭제 불가 에러 확인")
    public void cannotDeleteException() throws Exception {
        //given

        //when

        //then

    }
}
