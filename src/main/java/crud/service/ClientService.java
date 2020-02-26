package crud.service;

import crud.dao.ClientDAO;
import crud.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientDAO clientDAO;

    public Client findClientById(Integer id) {
        return clientDAO.findById(id).orElse(null);
    }

    public Client updateClient(Client client) {
        return clientDAO.save(client);
    }

    public Client addClient(Client client) {
        return clientDAO.save(client);
    }

    public void deleteClient(Client client) {
        clientDAO.delete(client);
    }

    public void deleteClientById(Integer id) {
        clientDAO.deleteById(id);
    }

    public Iterable<Client> findAllClients() {
        return clientDAO.findAll();
    }

    public void mergeClients(int id1, int id2) {
        Client client1 = findClientById(id1);
        Client client2 = findClientById(id2);
        if (client1 != null && client2 != null) {
            Client resultClient = new Client();
            resultClient.setRiskProfile(
                    client1.getRiskProfile().getRiskLevel() > client2.getRiskProfile().getRiskLevel()
                            ? client1.getRiskProfile() : client2.getRiskProfile());
            addClient(resultClient);
            deleteClient(client1);
            deleteClient(client2);
        }
    }

//    public Client

}
