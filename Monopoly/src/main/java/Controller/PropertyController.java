package Controller;

import Model.Fields.CompanyField;
import Model.Fields.Field;
import Model.Fields.PropertyField;
import Model.Player;


public class PropertyController extends Controller {
    public static final String[] PropertyActions = new String[]{
            "Køb ejendom",
            "Spring over"
           
    };

    public static final String[] CompanyActions = new String[]{
            "Køb firma",
            "Spring over"
          
    };


    public static final String[] PropertyManagementActions = new String[]{
            "Køb/Sælg bygninger",
            "Afslut tur"
    };

    public static final String[] BuySellBuildingActions = new String[]{
            "Køb hus",
            "Køb hotel",
            "Sælg hus",
            "Sælg hotel",
            "Tilbage"
    };

    PropertyField chosenField = null;
    boolean boughtBuilding = false;

    public PropertyController(GameController gameController){
        super(gameController, PropertyManagementActions);
    }

    @Override
    public String handleActions(String action){
        String[] newMenu = new String[]{"Afslut Tur"};

        switch (action){
            case "Afslut tur":
                gameController.getGame().endPlayerTurn();
                break;
            case "Spring over":
                newMenu = PropertyManagementActions;
                break;

            case "Køb firma":
                newMenu = gameController.buildPropertyMenu();
                buyCompanyField();
                break;
            case "Køb ejendom":
                newMenu = PropertyManagementActions;
                gameController.buyFieldPlayerIsOn(gameController.getGame().getActivePlayer());
                break;
            case "Køb/Sælg bygninger":
                newMenu = buildingNameMenu();
                setDropdown(true);
                break;
            case "Tilbage":
                newMenu = PropertyManagementActions;
                break;
            case "Køb hus":
                buyBuilding(0);
                boughtBuilding = true;
                newMenu = manageBuildingsMenu();
                break;
            case "Køb hotel":
                buyBuilding(1);
                boughtBuilding = true;
                newMenu = manageBuildingsMenu();
                break;
            case "Sælg hus":
                sellBuilding(0);
                newMenu = manageBuildingsMenu();
                break;
            case "Sælg hotel":
                sellBuilding(1);
                newMenu = manageBuildingsMenu();
                break;

            default:
                setChosenField(gameController.getGame().getGameBoard().getPropertyFieldByName(action));
                newMenu = manageBuildingsMenu();
                setDropdown(false);
                break;
        }

        super.setMenuActions(newMenu);
        return action;
    }

    private void buyCompanyField(){
        Player activePlayer = gameController.getGame().getActivePlayer();
        String companyFieldName = gameController.getGameBoard().getFieldModel(
                activePlayer.getField()
        ).getName();
        CompanyField companyField = gameController.getGame().getGameBoard().getCompanyFieldByName(companyFieldName);

        companyField.buyField(activePlayer);
    }


    public String[] buildingNameMenu(){
        return gameController.getGame().getGameBoard().getPlayerPropertyNames(gameController.getGame().getActivePlayer());
        
    }

    public String[] manageBuildingsMenu(){
        if (!boughtBuilding){
            String[] buyMenu = makeBuyMenu();
            String[] sellMenu = makeSellMenu();

            String[] finalMenu = new String[buyMenu.length + sellMenu.length];
            for (int i = 0; i < finalMenu.length; i++){
                if (i < buyMenu.length){
                    finalMenu[i] = buyMenu[i];
                } else {
                    finalMenu[i] = sellMenu[i - buyMenu.length];
                }
            }

            return finalMenu;
        } else {
            return makeSellMenu();
        }
    }

    private String[] makeSellMenu(){
        if (chosenField.getHouses() > 0 && !chosenField.isHotel()){
            return new String[]{
                    
                    PropertyController.BuySellBuildingActions[2], // sælgHus
                    PropertyController.BuySellBuildingActions[4] // Tilbage
            };
        } else if (chosenField.isHotel()){
            return new String[]{
                   
                    PropertyController.BuySellBuildingActions[3], // sælgHotel
                    PropertyController.BuySellBuildingActions[4]
            };
        } else {
            return new String[]{
                    PropertyController.BuySellBuildingActions[4]
            };
        }
    }

    private String[] makeBuyMenu(){
        if (chosenField.getHouses() < 4 && !chosenField.isHotel()){
            return new String[]{
                   
                    PropertyController.BuySellBuildingActions[0], // køb Hus
            };
        } else if (!chosenField.isHotel()){
            return new String[]{
                   
                    PropertyController.BuySellBuildingActions[1], // køb Hotel
            };
        } else {
            return new String[]{};
        }
    }

    public void buyBuilding(int type){
        if (!boughtBuilding){
            if (type == 0){
                chosenField.buyHouse();
            } else {
                chosenField.buyHotel();
            }
        }
    }

    public void sellBuilding(int type){
        if (type == 0){
            chosenField.sellHouse();
        } else {
            chosenField.sellHotel();
        }
    }

    public Field getChosenField(){
        return chosenField;
    }

    public void setChosenField(PropertyField chosenField){
        this.chosenField = chosenField;
    }

    public boolean isBoughtBuilding(){
        return boughtBuilding;
    }

    public void setBoughtBuilding(boolean boughtBuilding){
        this.boughtBuilding = boughtBuilding;
    }
}

