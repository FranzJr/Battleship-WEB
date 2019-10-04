package war.naval.game;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import war.naval.model.Game;

@Controller
public class GameController {

	@PostMapping("/create-game")
	public String index(@RequestParam String emailToGame, HttpSession session, Model model) {

		// systemgoal.gq:8091/app/game/battlefield/create?idGame=2&username=claudia-bermudez@upc.edu.co
		if (!StringUtils.isEmpty(emailToGame)) {
			try {

				if (session.getAttribute("game") == null) {
					RestTemplate rest = new RestTemplate();
					String URLCreateGame = "http://systemgoal.gq:8091/app/game/create?username=" + emailToGame;

					ResponseEntity<Game> response = rest.postForEntity(URLCreateGame, null, Game.class);

					if (response.getStatusCode() == HttpStatus.OK) {
						Game game = response.getBody();
						session.setAttribute("game", game);
						session.setAttribute("email", emailToGame);

						return "battlefield/battlefield";
					} else {
						model.addAttribute("GameError", "Hubo un error en la consulta del servicio");
						model.addAttribute("DetailError", response.getStatusCode());
					}
				} else {
					return "battlefield/battlefield";
				}

			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("GameError", "Hubo un error en la consulta del servicio");
				model.addAttribute("DetailError", e.getMessage());
			}

		} else {
			model.addAttribute("GameError", "Hay campos obligatorios sin diligenciar");
		}

		return "home/home";
	}

}
