package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by seungwoo.song on 2022-11-08
 */
class DeleteHistoryTest {

    public static final DeleteHistory QUESTION_DELETEHISTORY = new DeleteHistory(ContentType.QUESTION, QuestionTest.Q1.getId(), QuestionTest.Q1.getWriter(), LocalDateTime.now());
    public static final DeleteHistory ANSWER_DELETEHISTORY = new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now());
}
