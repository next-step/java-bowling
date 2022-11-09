package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

    @Test
    void 모든_답변_삭제_성공() throws CannotDeleteException {
        Answers answers = getSameWriterAnswers();

        List<DeleteHistory> deleteHistories = answers.deleteAll(UserTest.JAVAJIGI);
        assertAll(
                () -> assertThat(deleteHistories).hasSize(2),
                () -> assertThat(deleteHistories.get(0).getContentType()).isEqualTo(ContentType.ANSWER),
                () -> assertThat(deleteHistories.get(0).getDeletedBy()).isEqualTo(UserTest.JAVAJIGI),
                () -> assertThat(deleteHistories.get(1).getContentType()).isEqualTo(ContentType.ANSWER),
                () -> assertThat(deleteHistories.get(1).getDeletedBy()).isEqualTo(UserTest.JAVAJIGI)
        );
    }

    @Test
    void 하나라도_작성자가_다르면_삭제_시_예외_발생() {
        Answers answers = getMixWriterAnswers();

        AtomicReference<List<DeleteHistory>> deleteHistories = new AtomicReference<>();
        assertAll(
                () -> assertThatThrownBy(() -> answers.deleteAll(UserTest.JAVAJIGI))
                        .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다."),
                () -> assertThat(deleteHistories.get()).isNull());
    }

    private Answers getSameWriterAnswers() {
        Answers answers = new Answers();
        answers.add(getAnswer(UserTest.JAVAJIGI));
        answers.add(getAnswer(UserTest.JAVAJIGI));
        return answers;
    }

    private Answers getMixWriterAnswers() {
        Answers answers = new Answers();
        answers.add(getAnswer(UserTest.JAVAJIGI));
        answers.add(getAnswer(UserTest.SANJIGI));
        return answers;
    }

    private Answer getAnswer(User user) {
        return new Answer(user, QuestionTest.Q1, "Answers Contents");
    }

}
