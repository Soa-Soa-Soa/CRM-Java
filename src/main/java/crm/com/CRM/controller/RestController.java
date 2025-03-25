package crm.com.CRM.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class RestController {

    @Autowired
    private RestTemplate restTemplate;

    @SuppressWarnings("unchecked")
    @GetMapping("/getUsers")
    public String getAllUsers(Model model) {
        String API_URL = "http://127.0.0.1:8000/api/users";
        List<Map<String, Object>> users = restTemplate.getForObject(API_URL, List.class);
        model.addAttribute("users", users);

        return "users";
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/getClients")
    public String getAllClients(Model model) {
        String API_URL = "http://127.0.0.1:8000/api/clients";
        List<Map<String, Object>> clients = restTemplate.getForObject(API_URL, List.class);
        model.addAttribute("clients", clients);

        return "clients";
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/getClients/{id}")
    public String getClientId(Model model, @PathVariable("id") String id) {
        String API_URL = "http://127.0.0.1:8000/api/clients/" + id;
        Map<String, Object> data = restTemplate.getForObject(API_URL, Map.class);
        model.addAttribute("data", data);

        return "showClients";
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/getLeads")
    public String getAllLeads(Model model) {
        String API_URL = "http://127.0.0.1:8000/api/leads";
        List<Map<String, Object>> leads = restTemplate.getForObject(API_URL, List.class);
        model.addAttribute("leads", leads);

        return "leads";
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/getLeads/{id}")
    public String getLeadsId(Model model, @PathVariable("id") String id) {
        String API_URL = "http://127.0.0.1:8000/api/leads/" + id;
        Map<String, Object> data = restTemplate.getForObject(API_URL, Map.class);
        model.addAttribute("data", data);

        return "showLeads";
    }
}
 