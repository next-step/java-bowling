package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {
    public Answer A1;
    public Answer A2;
    public Answer A3;

    @BeforeEach
    void setup(){
        A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");
    }

    @Test
    @DisplayName("리플 삭제시 본인이 작성한 댓글만 존재하면 deleted 상태를 true 로 변경한다")
    void delete() throws CannotDeleteException {
        assertThat(A1.isDeleted()).isFalse();
        assertThat(A3.isDeleted()).isFalse();
        new Answers(List.of(A1, A3)).delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
        assertThat(A3.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("리플 삭제시 다른 유저가 작성한 댓글이 있으면 예외가 발생한다")
    void deleteException() throws CannotDeleteException {
        Answers answers = new Answers(List.of(A1, A2));
        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("add 메소드로 answer 객체를 추가한다")
    void add() {
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A2);
        assertThat(answers).isEqualTo(new Answers(List.of(A1, A2)));
    }
}
