package Model;

import Model.ChanceCards.ChanceCard;
import Model.Fields.Field;

/**
 *  player class in monopoly game with values
 */
public class Player {
    private int previousField = 0;
    private int field = 0;
    private String name;
    private int currentMoney = 1;
    private int lastDiceResult = 0;
    private int[] lastDicePair;
    private boolean inJail = false;

    private ChanceCard chanceCard;
    private boolean chanceField = false;
    private boolean outOfJailFree = false;
    private boolean Lucky = false;
    private int Jailtime = 0;

    private String lastAction = "";

    private boolean bankrupt = false;

    public Player(String name){
        // Vælg spiller name selv
        this.name = name;
    }


    public int getField(){
        return field;
    }

    public int setField(int newField){
       
        field = newField;
        return field;
    }

    public boolean isInJail(){
        return inJail;
    }

    public void setInJail(boolean inJail){
        this.inJail = inJail;
    }

    public int getMoney(){
        return this.currentMoney;
    }

    void setMoney(int money){
        this.currentMoney = money;
    }

    public void setLastDiceResult(int lastDiceResult){
        this.lastDiceResult = lastDiceResult;
    }

    public void setLastDicePair(int[] lastDicePair){
        this.lastDicePair = lastDicePair;
    }

    public void setChanceCard(ChanceCard chanceCard){
        this.chanceCard = chanceCard;
    }

    public void setOutOfJailFree(boolean outOfJailFree){
        this.outOfJailFree = outOfJailFree;
    }

    public void setChanceField(boolean chanceField){
        this.chanceField = chanceField;
    }

    public boolean isBankrupt(){
        return this.bankrupt;
    }

    public void setBankrupt(boolean bankrupt){
        this.bankrupt = bankrupt;
    }

    public String getName(){
        // Returnerer spiller navn
        return name;
    }

    public int getLastDiceResult(){
        return lastDiceResult;
    }

    public int[] getLastDicePair(){
        return lastDicePair;
    }

    public int[] getDicePair(){
        return null;
    }

    public ChanceCard getChanceCard(){
        return chanceCard;
    }

    public boolean isOutOfJailFree(){
        return outOfJailFree;
    }

    public boolean isChanceField(){
        return chanceField;
    }

    public String getLastAction(){
        return lastAction;
    }

   public void addMoney(int money){
        this.currentMoney = currentMoney + money;
    }

   public void setLastAction(String lastAction){
        this.lastAction = lastAction;
    }

    public String toString(){
        if (this.lastAction.equals("")){
            return "Spiller: " + this.getName() + " er landet på " + (this.getField() + 1) + ".";
        } else {
            return "Spiller: " + this.getName() + " " + this.getLastAction();
        }
    }

    public int getPreviousField(){
        return previousField;
    }

    public void setPreviousField(int previousField){
        this.previousField = previousField;
    }

    /**
     * A player can be put in a situation where they need to play 10% of their current currency amount.
     */
    public void payTenPercent(){
        int payment = currentMoney / 10;
        addMoney(-payment);
    }

    public boolean isLucky(){
        return Lucky;
    }

    public void setLucky(boolean lucky){
        Lucky = lucky;
    }

    public int getJailtime() {
        return Jailtime;
    }

    public void setJailtime(int jailtime) {
        Jailtime = jailtime;
    }
    public void addJailtime(int jailtime){
        Jailtime = Jailtime + jailtime;
    }
}
