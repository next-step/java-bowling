package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("삭제 테스트")
    @Test
    public void deleteTest() {
        //given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        //when
        question.delete();

        //then
        assertThat(question.isDeleted()).isEqualTo(true);
    }

    @DisplayName("삭제되지 않았을 때 히스토리 작성 테스트")
    @Test
    public void makeDeleteHistoryListWhenNotDeletedTest() {
        //given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        //then
        assertThatIllegalStateException().isThrownBy(() -> question.makeDeleteHistoryList());
    }

    @DisplayName("다른 답변자가 있을 때 테스트")
    @Test
    public void containsAnotherUserAnswerTest() {
        //given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.SANJIGI, question, "Answers Contents1"));

        //when
        boolean actual = question.containsAnotherUserAnswer();

        //then
        assertThat(actual).isEqualTo(true);
    }

    @DisplayName("본인 답변만 있을 때 테스트")
    @Test
    public void nonContainsAnotherUserAnswerTest() {
        //given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents1"));

        //when
        boolean actual = question.containsAnotherUserAnswer();

        //then
        assertThat(actual).isEqualTo(false);
    }


}
