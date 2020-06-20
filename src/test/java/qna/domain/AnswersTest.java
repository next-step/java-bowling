package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;
import qna.fixture.Fixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswersTest {

    private final Answers answers = Fixture.of().getAnswers();

    @DisplayName("답변 리스트를 반환")
    @Test
    public void getAnswers() {
        Answer answer1 = Fixture.of().getAnswer1();
        Answer answer2 = Fixture.of().getAnswer2();
        Answers answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);


        assertThat(answers.getAnswers())
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
        Answers answers = new Answers();
        answers.add(Fixture.of().getAnswer1());
        answers.add(Fixture.of().getAnswer1());

        assertThat(answers.delete(Fixture.of().getJavajigi()).size())
                .isEqualTo(answers.getAnswers().size());
    }
}
