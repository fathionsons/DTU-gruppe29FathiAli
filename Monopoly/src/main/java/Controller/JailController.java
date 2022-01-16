package Controller;

import Model.Global;
import Model.Player;

public class JailController extends Controller {
    public static final String[] JailActions = new String[]{
            "Betal 1000 kr.",
            "Rul terning",
            "Brug løsladelseskort"
    };

    public JailController(GameController gameController){
        super(gameController, JailActions);
    }

    @Override
    public String handleActions(String action){
        switch (action){
            case "Betal 1000 kr.":
                payBail(gameController.getGame().getActivePlayer());
                break;
            case "Rul terning":
                if (gameController.getGame().getActivePlayer().getJailtime()>2){
                    gameController.getGame().getActivePlayer().setLastAction(gameController.getGame().getActivePlayer().getLastAction() + "\n - Har prøvet nok og skal betale for at komme ud");
                    System.out.println("[INFO] " + gameController.getGame().getActivePlayer().getName() + " det må du ikke");
                    return "Ikke mere Rul ternning";
                }
                feelingLucky(gameController.getGame().getActivePlayer());
                return "Jail Rul terning";
                //break;
            case "Brug løsladelseskort":
                bailCard(gameController.getGame().getActivePlayer());
                break;
            default:
                // Indsæt auktion funktionalitet?
                break;
            }
        return action;
    }


    public void payBail(Player player){
        player.addMoney(-Global.JAIL_PRICE);

        player.setLastAction(player.getLastAction() + "\n - Har betalt 1000 kr for at komme ud af fængsel.");
        System.out.println("[INFO] " + player.getName() + " har betalt 1000 kr for at komme ud af fængsel.");
        player.setInJail(false);
        player.setJailtime(0);
    }

    public void feelingLucky(Player player){



        int diceThrowResult = gameController.getGame().setAndGetDiceResult();
        int[] diceThrow = gameController.getGame().getDicePair();

        if (diceThrow[0] != diceThrow[1]){
            player.setLastAction(player.getLastAction() + "\n - Slog " + diceThrow[0] + " og " + diceThrow[1] + " og kom ikke ud af fængsel.");
            System.out.println("[INFO] " + player.getName() + " har slået " + diceThrow[0] + " og " + diceThrow[1] + " så de kom ikke ud af fængsel.");
            player.setLucky(false);
            player.addJailtime(1);
            player.setLastDiceResult(diceThrowResult);
            player.setLastDicePair(diceThrow);
            gameController.updateDice(player);

        } else if (diceThrow[0] == diceThrow[1]){
            player.setLastAction(player.getLastAction() + "\n - Slog " + diceThrow[0] + " to gange og kom ud af fængsel.");
            System.out.println("[INFO] " + player.getName() + " har slået " + diceThrow[0] + " to gange og kom ud af fængsel, hvor de så bevægede sig med " + diceThrowResult + " felter.");
            player.setInJail(false);
            player.setLucky(true);

            player.setJailtime(0);
            player.setLastDiceResult(diceThrowResult);
            player.setLastDicePair(diceThrow);
            gameController.getGame().throwDice(true);

            gameController.updateDice(player);
            gameController.updateUIPlayer(player);

        } else {
            System.out.println("Something went wrong");
        }

    }

    public void bailCard(Player player){
        if(player.isOutOfJailFree()){
            player.setLastAction(player.getLastAction() + "\n - Har brugt sit løsladelseskort.");
            System.out.println("[INFO] " + player.getName() + " har brugt sit løsladelseskort");
            player.setInJail(false);
            player.setOutOfJailFree(false);
            player.setJailtime(0);
        } else {
            player.setLastAction(player.getLastAction() + "\n - Har ikke et løsladelseskort.");
            System.out.println("[INFO] " + player.getName() + " Har ikke et løsladelseskort.");
        }
    }
}
