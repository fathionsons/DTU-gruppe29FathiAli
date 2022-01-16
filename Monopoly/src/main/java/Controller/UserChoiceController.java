package Controller;

import Model.Global;
import Model.Player;

public class UserChoiceController extends Controller {
    public static final String[] UserChoiceActions = new String[]{
            "Betal 10%",
            "Betal 4000 kr."
    };

    public UserChoiceController(GameController gameController){
        super(gameController, UserChoiceActions);
    }

    @Override
    String handleActions(String action){
        switch (action){
            case "Betal 10%":
                payTenPercent();
                break;
            case "Betal 4000 kr.":
                payConstant();
                gameController.getGame().getActivePlayer().payTenPercent();
                break;
        }

        return action;
    }

    public void payTenPercent(){
        Player player = gameController.getGame().getActivePlayer();
        player.payTenPercent();


        player.setLastAction(player.getLastAction() + "\n - Har betalt 10% af deres formue i indkomstskat.");
        System.out.println("[INFO] " + player.getName() + " har betalt 10% af deres formue i indkomstskat.");

    }

    public void payConstant(){
        gameController.getGame().getActivePlayer().addMoney(-Global.FIELD_5);

        Player player = gameController.getGame().getActivePlayer();
        player.setLastAction(player.getLastAction() + "\n - Har betalt 4000 kr. i indkomstskat.");
        System.out.println("[INFO] " + player.getName() + " har betalt 4000 kr. i indkomstskat.");

    }


}
