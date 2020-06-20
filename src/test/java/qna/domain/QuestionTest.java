package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;
import qna.fixture.Fixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {

    private final Question question1 = Fixture.of().getQuestion1();
    private final Question question2 = Fixture.of().getQuestion2();

    @DisplayName("로그인한 유저가 작성자가 아닌 경우 예외 발생")
    @Test
    public void verifyOwner() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question1.verifyOwner(Fixture.of().getSanjigi()));
    }

    @DisplayName("질문 삭제 시 삭제 상태(deleted)를 TRUE로 변경, (답변이 없는 경우)")
    @Test
    public void deleteState() {
        question2.delete(Fixture.of().getSanjigi());

        assertThat(question2.isDeleted()).isTrue();
    }

    @DisplayName("답변이 있는 경우 질문자와 답변글의 모든 답변자가 같으면 삭제가 가능")
    @Test
    public void deleteWhenAllAnswersAreWrittenByWriter() {
        final Answer answer = Fixture.of().getAnswer1();
        question1.addAnswer(answer);
        question1.addAnswer(answer);
        question1.addAnswer(answer);

        question1.delete(Fixture.of().getJavajigi());

        assertThat(question1.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 답변자가 다른 경우 답변을 삭제 시, 예외 발생")
    @Test
    public void deleteFailure() {
        question1.addAnswer(Fixture.of().getAnswer2());

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question1.delete(Fixture.of().getJavajigi()));
    }
}
