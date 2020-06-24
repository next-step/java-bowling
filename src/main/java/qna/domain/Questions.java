package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Questions {

    private List<Question> questions = new ArrayList<>();

    public Questions (Question question) {
        questions.add(question);
    }

    public List<Answer> getAnswers(long questionId) {
        return questions.stream()
                .filter(question -> question.getId() == questionId)
                .findFirst()
                .map(Question::getAnswers).orElseThrow(IllegalAccessError::new);
    }

    public void deleteQuestion(long questionId, User user, DeleteHistories deleteHistories) throws CannotDeleteException {
        Question deleteQuestion = questions.stream()
                                .filter(question -> question.getId() == questionId && question.isOwner(user))
                                .findFirst()
                                .orElseThrow(() -> new CannotDeleteException("질문을 삭제할 권한이 없습니다."));
        deleteQuestion.setDeleted(true);
        deleteHistories.addDeleteHistory(new DeleteHistory(ContentType.QUESTION, questionId, deleteQuestion.getWriter()));
    }


}
