package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제를 성공한다.")
    void deleteSuccess() {

    }

    @Test
    @DisplayName("다른 사람이 쓴 글은 삭제할수 없다.")
    void throwAnotherWrite() {
    }

    @Test
    @DisplayName("질문자와 답변자가 같을경우 삭제가 가능하다.")
    void deleteSuccessSameUser() {
    }



}
