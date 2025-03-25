package crm.com.CRM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import crm.com.CRM.service.TokenStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.HashMap;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Value("${php.api.base-url}")
    private String phpApiBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenStorage tokenStorage;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            // Préparer les headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Préparer le body
            Map<String, String> body = new HashMap<>();
            body.put("email", email);
            body.put("password", password);

            // Créer l'entité HTTP
            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

            System.out.print("Tentative de connexion pour l'email: {}"+ email);
            System.out.print("URL de l'API: {}"+ phpApiBaseUrl + "/api/login");

            // Faire la requête
            ResponseEntity<Map> response = restTemplate.exchange(
                phpApiBaseUrl + "/api/login",
                HttpMethod.POST,
                requestEntity,
                Map.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String token = (String) response.getBody().get("token");
                if (token != null && !token.isEmpty()) {
                    tokenStorage.setToken(token);
                    System.out.print("Connexion réussie pour l'email: {}"+ email);
                    return "redirect:/dashboard";
                }
            }

            logger.error("Échec de la connexion: réponse invalide de l'API");
            model.addAttribute("error", "Email ou mot de passe incorrect");
            return "login";

        } catch (Exception e) {
            logger.error("Erreur de connexion: {}"+ e.getMessage(), e);
            model.addAttribute("error", "Erreur de connexion: " + e.getMessage());
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout() {
        tokenStorage.setToken(null);
        return "redirect:/login";
    }
}