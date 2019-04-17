package controller;

import domain.game.BowlingGame;
import launcher.WebApplicationLauncher;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;
import util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static launcher.WebApplicationLauncher.getCurrentGame;
import static launcher.WebApplicationLauncher.getGames;

public abstract class AbstractController {
    protected static final String INDEX_TEMPLATE_PATH = "index.html";

    protected String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    protected void redirectToIndex(Response response) {
        response.redirect(INDEX_TEMPLATE_PATH);
    }

    protected String getInputLine(Request req, String key) {
        String inputLine = req.queryParams(key);
        if (StringUtils.isEmpty(inputLine)) {
            throw new IllegalArgumentException("잘못된 입력값입니다.");
        }

        return inputLine;
    }

    protected Map<String, Object> putAndGetModel() {
        Map<String, Object> model = new HashMap<>();
        model.put("games", getGames());
        model.put("currentPlayerName", getCurrentPlayerName());

        int currentFrameNumber = getCurrentFrameNumber();
        model.put("currentFrameNumber", currentFrameNumber);
        model.put("currentBowl", getCurrentBowl(currentFrameNumber));

        return model;
    }

    private String getCurrentPlayerName() {
        return getCurrentGame().getPlayerName();
    }

    private int getCurrentFrameNumber() {
        return Optional.ofNullable(WebApplicationLauncher.getCurrentGame())
                .map(BowlingGame::getRecentFrame)
                .map(frame -> {
                    if (frame.isFinished() && !frame.isLastFrame()) {
                        return frame.getNumber() + 1;
                    }

                    return frame.getNumber();
                })
                .orElse(1);
    }

    private int getCurrentBowl(int currentFrameNumber) {
        return Optional.ofNullable(WebApplicationLauncher.getCurrentGame())
                .map(game -> {
                    if (game.getFramesSize() < currentFrameNumber) {
                        return 1;
                    }

                    return game.getFrame(currentFrameNumber - 1).getStatusesSize() + 1;
                })
                .orElse(1);
    }
}
