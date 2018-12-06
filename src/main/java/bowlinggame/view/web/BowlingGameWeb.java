package bowlinggame.view.web;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import bowlinggame.domain.MultiBowlingGame;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Session;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class BowlingGameWeb {

	public static void main(String[] args) {
		port(8080);

		get("/", (req, res) -> render(null, "index.html"));

		post("/start", (req, res) -> {
			MultiBowlingGame bowlingGame = new MultiBowlingGame(req.queryParams("players"));
			Session session = req.session();
			session.attribute("game", bowlingGame);

			return getModel(bowlingGame);
		});

		post("/throw", (req, res) -> {
			MultiBowlingGame bowlingGame = req.session().attribute("game");
			bowlingGame.play(Integer.parseInt(req.queryParams("throwingNo")));

			return getModel(bowlingGame);
		});
	}

	private static String getModel(MultiBowlingGame bowlingGame) {
		Map<String, Object> model = new HashMap<>();
		model.put("currentFrameNumber", bowlingGame.getCurrentFrameNumber());
		model.put("currentPlayer", bowlingGame.getCurrentPlayer());
		model.put("result", bowlingGame.result());
		model.put("isGameOver", bowlingGame.isGameOver());
		return render(model, "play.html");
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
