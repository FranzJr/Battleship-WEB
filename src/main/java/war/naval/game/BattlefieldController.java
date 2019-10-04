package war.naval.game;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import war.naval.dto.ShipDto;
import war.naval.model.Game;

@Controller()
public class BattlefieldController {

	@ResponseBody
	@PostMapping("/battlefield/create")
	public String index(@RequestBody List<ShipDto> ships, HttpSession session, Model model) {

		try {

			if (session.getAttribute("game") != null && session.getAttribute("email") != null) {

				Game game = (Game) session.getAttribute("game");
				String email = (String) session.getAttribute("email");

				RestTemplate rest = new RestTemplate();
				String URLCreateGame = "http://systemgoal.gq:8091/app/battlefield/create?idGame=" + game.getId() + "&username="
						+ email;

				ResponseEntity<Collection> response = rest.postForEntity(URLCreateGame, ships, Collection.class);

				if (response.getStatusCode() == HttpStatus.OK) {
			        Gson gson = new Gson();

					session.setAttribute("ships", gson.toJson(ships));
					return "/battlefield/war";
				} else {
					model.addAttribute("GameError", "Hubo un error en la consulta del servicio");
					model.addAttribute("DetailError", response.getStatusCode());
				}
			} 

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("GameError", "Hubo un error en la consulta del servicio");
			model.addAttribute("DetailError", e.getMessage());
		}

		return "";
	}
	
	@GetMapping("/battlefield/war")
	public String war(HttpSession session, Model model) {
		
		if (session.getAttribute("game") == null) {
			return "home/home";
		}
		return "/battlefield/war";

	}

}
