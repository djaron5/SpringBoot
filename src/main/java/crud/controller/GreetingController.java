package crud.controller;

import crud.model.Client;
import crud.model.RiskProfile;
import crud.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("/")
public class GreetingController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("riskProfiles", RiskProfile.values());
        model.addAttribute("clients", clientService.findAllClients());
        return "greeting";
    }

    @PostMapping
    public String addClient(@ModelAttribute("riskProfile") String riskProfile, Model model) {
        Client client = new Client();
        client.setRiskProfile(RiskProfile.valueOf(riskProfile));
        clientService.addClient(client);
        model.addAttribute("riskProfiles", RiskProfile.values());
        model.addAttribute("clients", clientService.findAllClients());
        return "greeting";
    }

    @PostMapping("filter")
    public String filterById(@RequestParam(name = "byId", defaultValue = "-1") Integer id, Model model) {
        System.out.println(id.toString());
        model.addAttribute("riskProfiles", RiskProfile.values());
        if (id == -1) {
            model.addAttribute("clients", clientService.findAllClients());
        } else {
            model.addAttribute("clients", clientService.findClientById(id));
        }
        return "greeting";
    }

    @PostMapping("update")
    public String filterById(@RequestParam(name = "id", defaultValue = "-1") Integer id, @RequestParam(name = "riskProfile") String riskProfile, Model model) {
        if (id != -1) {
            Client client = clientService.findClientById(id);
            if (client != null && !riskProfile.isEmpty()) {
                client.setRiskProfile(RiskProfile.valueOf(riskProfile));
                clientService.updateClient(client);
            }
        }

        model.addAttribute("riskProfiles", RiskProfile.values());
        model.addAttribute("clients", clientService.findAllClients());
        return "greeting";
    }

    @PostMapping("delete")
    public String deleteById(@RequestParam(name = "id", defaultValue = "-1") Integer id, Model model) {
        if (id != -1) {
            Client client = clientService.findClientById(id);
            if (client != null) {
                clientService.deleteClient(client);
            }
        }

        model.addAttribute("riskProfiles", RiskProfile.values());
        model.addAttribute("clients", clientService.findAllClients());
        return "greeting";
    }

    @PostMapping("merge")
    public String doGet(@RequestParam(name = "id1", defaultValue = "-1") Integer id1,
                        @RequestParam(name = "id2", defaultValue = "-1") Integer id2,
                        Model model) {
        if (id1 != -1 && id2 != -1)
            clientService.mergeClients(id1, id2);
        model.addAttribute("riskProfiles", RiskProfile.values());
        model.addAttribute("clients", clientService.findAllClients());
        return "greeting";
    }
}
