package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("삭제 테스트")
    @Test
    public void deleteTest() {
        //given
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        //when
        answer.delete();
        //then
        assertThat(answer.isDeleted()).isEqualTo(true);
    }

    @DisplayName("삭제 히스토리 작성 테스트")
    @Test
    public void makeDeleteHistoryTest() {
        //given
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        //when
        answer.delete();
        DeleteHistory history = answer.makeDeleteHistory();

        //then
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());
        assertThat(history).isEqualTo(expected);
    }

    @DisplayName("삭제 되지 않은 객체 삭제 히스토리 작성 테스트")
    @Test
    public void makeDeleteHistoryNotDeletedAnswerTest() {
        //given
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        assertThatIllegalStateException().isThrownBy(() -> answer.makeDeleteHistory());
    }
}
