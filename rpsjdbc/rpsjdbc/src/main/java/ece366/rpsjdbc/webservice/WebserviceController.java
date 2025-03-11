package ece366.rpsjdbc.webservice;

import ece366.rpsjdbc.DatabaseConnectionManager;
import ece366.rpsjdbc.Player;
import ece366.rpsjdbc.PlayerDAO;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class WebserviceController {

    @GetMapping("/testPlayer")
    public String getTestPlayer() {
        return "TEST PLAYER";
    }

    @GetMapping("/player/{id}")
    public Player getPlayerById(@PathVariable Long id){
        System.out.println("getPlayerById");
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "rps", "postgres", "password");

        try {
            Connection connection = dcm.getConnection();
            PlayerDAO playerDAO = new PlayerDAO(connection);

            Player player = playerDAO.findById(id);
            System.out.println(player.toString());

            return player;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

