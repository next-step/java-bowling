package qna.service;

import qna.CannotDeleteException;
import qna.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class Questioners {

    private List<Question> questionList;
    private List<DeleteHistory> deleteHistories = new ArrayList<>();


    public Questioners(List<Question> questionList) {
        this.questionList = questionList;
    }

    public static Questioners of(List<Question> questionList) {
        return new Questioners(questionList);
    }

    public Question findQuestoner(User userId) throws CannotDeleteException {

        for (Question question : questionList) {
            if (question.isOwner(userId))
                return question;
        }
        throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
    }

    public List<Answer> findAnswer(User userId) throws CannotDeleteException {
        List<Answer> answers = new ArrayList<>();

        for (Answer answer : findQuestoner(userId).getAnswers()) {
            if (!answer.isOwner(userId)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
            answers.add(answer);
        }
        return answers;
    }

    public List<DeleteHistory> delete(User userId, long questionId) throws CannotDeleteException {

        Question question = findQuestoner(userId);
        question.setDeleted(TRUE);

        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));

        for (Answer answer : findAnswer(userId)) {
            answer.setDeleted(TRUE);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }

        return deleteHistories;

    }
}
