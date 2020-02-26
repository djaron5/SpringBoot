package crud.service;

import crud.model.Client;
import crud.model.RiskProfile;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClientServiceTest {
    @Autowired
    private ClientService clientService;

    private Client client;


    @BeforeEach
    void init() {
        client = new Client();
        client.setRiskProfile(RiskProfile.HIGHT);
    }

    @Test
    void test() {
        Assert.assertNotNull(clientService);
    }

    @Test
    void addClient() {
        Assert.assertNotNull(clientService.addClient(client));
        Assert.assertTrue(client.getId() != 0);
    }

    @Test
    void findClientById() {
        clientService.addClient(client);
        Assert.assertNotNull(clientService.findClientById(client.getId()));
    }

    @Test
    void updateClient() {
        client.setRiskProfile(RiskProfile.LOW);
        clientService.updateClient(client);
        Assert.assertSame(clientService.findClientById(client.getId()).getRiskProfile(), RiskProfile.LOW);
    }
}