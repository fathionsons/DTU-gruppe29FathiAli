package Controller;

public class EndTurnController extends Controller {
    public static final String[] EndActions = new String[]{"Afslut tur"};

    public EndTurnController(GameController game){
        super(game, EndActions);
    }

    @Override
    String handleActions(String action){

        switch (action){
            case "Afslut tur":
                gameController.getGame().endPlayerTurn();
                break;
            default:
                break;
        }
        return "Afslut tur";
    }

}
