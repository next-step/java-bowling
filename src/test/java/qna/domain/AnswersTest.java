package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

public class AnswersTest {
    private Answer answer1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private Answer answer2 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private Answers answers = new Answers(Arrays.asList(answer1, answer2));

    @Test
    @DisplayName("삭제 가능 테스트")
    public void 삭제가능_테스트() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1));
        assertThat(answers.isDeletable(AnswerTest.A1.getWriter()))
                .isTrue();
    }

    @Test
    @DisplayName("삭제 불가능 테스트")
    public void 삭제불가능_테스트() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
        assertThat(answers.isDeletable(AnswerTest.A1.getWriter()))
                .isFalse();
    }

    @Test
    @DisplayName("삭제 테스트")
    public void 삭제_테스트() throws CannotDeleteException {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1));
        assertThat(answers.delete(AnswerTest.A1.getWriter()))
                .containsExactly(new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now()));
    }

}
