package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class AnswersTest {

    @Test
    void 모든_답변_삭제_성공() {
        Answers answers = getSameWriterAnswers();
        assertThat(answers.deleteAll(UserTest.JAVAJIGI).size()).isEqualTo(2);
    }

    @Test
    void 하나라도_작성자가_다르면_삭제_시_예외_발생() {
        Answers answers = getMixWriterAnswers();
        assertThatThrownBy(() -> answers.deleteAll(UserTest.JAVAJIGI))
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
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
