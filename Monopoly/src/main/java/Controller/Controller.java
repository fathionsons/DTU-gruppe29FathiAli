package Controller;

import Model.Game;

abstract public class Controller {
    private String[] menuActions;
    protected GameController gameController;
    protected boolean isDropdown = false;

    public Controller(GameController gameController, String[] menuActions){
        this.menuActions = menuActions;
        this.gameController = gameController;
    }

    abstract String handleActions(String action);

    public String[] getMenuActions(){
        return menuActions;
    }

    public void setMenuActions(String[] menuActions){
        this.menuActions = menuActions;
    }

    protected boolean isDropdown(){
        return isDropdown;
    }

    protected void setDropdown(boolean dropdown){
        isDropdown = dropdown;
    }
}

