package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;
import qna.fixture.Fixture;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswersTest {

    private final Answers answers = Fixture.of().getAnswers();

    @DisplayName("답변 리스트를 반환")
    @Test
    public void getAnswers() {
        Answer answer1 = Fixture.of().getAnswer1();
        Answer answer2 = Fixture.of().getAnswer2();
        List<Answer> answerList = Arrays.asList(answer1, answer2);

        assertThat(Answers.of(answerList).getAnswers())
                .containsExactly(answer1, answer2);
    }

    @DisplayName("질문자와 답변글의 모든 답변자가 같지 않은 경우 예외 발생")
    @Test
    public void isExistOtherCommenter() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answers.delete(Fixture.of().getJavajigi()));
    }

    @DisplayName("질문자와 답변글의 모든 답변자가 같은 경우 모든 답변글 삭제 후 DeleteHistory 리스트 반환")
    @Test
    public void deleteSuccess() {
        Answers answers = Answers.of(
                Arrays.asList(Fixture.of().getAnswer1(), Fixture.of().getAnswer1()));

        assertThat(answers.delete(Fixture.of().getJavajigi()).size())
                .isEqualTo(answers.getAnswers().size());
    }
}
