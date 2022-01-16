package Model;

import Model.Fields.*;
import Model.ChanceCards.ChanceCard;
import Model.ChanceCards.CardFactory;


import java.awt.*;

/**
 * Class containing all fields and chancecards including methods
 */
public class GameBoard {

    private Field[] fields;

    private ChanceCard[] chanceCards;

    public GameBoard(){
        this.fields = new Field[Global.FIELD_COUNT];

        this.chanceCards = makeCards();
        this.fields = makeFields();

    }

    //make fields
    private Field[] makeFields(){
        return FieldFactory.makeFields();
    }

    //get make cards
    private ChanceCard[] makeCards(){
        return CardFactory.makeCards();
    }

    public Field[] getFields(){
        return fields;
    }

  //get field model
    public Field getFieldModel(int index){
        return fields[index % Global.FIELD_COUNT];
    }

    // check os owned by any player
    public boolean isOwned(int index){
        Field field = this.getFields()[index % Global.FIELD_COUNT];
        if (field instanceof PropertyField){
            Player owner = ((PropertyField) field).getOwner();
            return owner != null;
        }
        return false;
    }

   //get jail status
    public int getJail(){
        return Global.JAIL_INDEX;
    }

    public ChanceCard[] getChanceCards(){
        return chanceCards;
    }

    public void setChanceCards(ChanceCard[] chanceCards){
        this.chanceCards = chanceCards;
    }

    //random chance card
    public ChanceCard randomChanceCard(){
        // Generate a random number (float)
        float _random1 = (float) Math.random();
        // Scale number up to length of all ChanceCards, casting to int
        int _random2 = (int) (_random1 * (this.getChanceCards().length - 1));
        int nr = _random2 + 1;

        return this.getChanceCards()[nr];
    }

    
    public int closestColor(int index, Color color){
        Field[] fields = this.getFields();

        for (int i = 0; i < fields.length; i++){
            int correctIndex = i + index;
            Field tempField = fields[correctIndex % Global.FIELD_COUNT];

            if (tempField instanceof PropertyField &&
                    ((PropertyField) tempField).getColor() == color){
                return correctIndex % Global.FIELD_COUNT;
            }
        }

        return -1;
    }

    
    public int closestName(int index, String name){
        Field[] fields = this.getFields();

        for (int i = 0; i < fields.length; i++){
            int correctIndex = i + index;
            Field tempField = fields[correctIndex % Global.FIELD_COUNT];

            if (tempField.getName() == name){
                return correctIndex % Global.FIELD_COUNT;
            }
        }
        return -1;
    }

    
    public int getClosestShipping(int index){
        Field[] fields = this.getFields();

        for (int i = 0; i < fields.length; i++){
            int correctIndex = i + index;
            Field tempField = fields[correctIndex % Global.FIELD_COUNT];

            if (tempField instanceof CompanyField &&
                    ((CompanyField) tempField).isShipping()){
                return correctIndex % Global.FIELD_COUNT;
            }
        }
        return -1;
    }

  
    public PropertyField[] getPlayerProperties(Player player){

        PropertyField[] tempProperties = new PropertyField[Global.COLORED_PROPERTIES];
        int counter = 0;

        for (int i = 0; i < fields.length; i++){
            if (fields[i] instanceof PropertyField && ((PropertyField) fields[i]).getOwner() == player){
                tempProperties[counter] = (PropertyField) fields[i];
                counter++;
            }
        }

        if (counter == 0){
            return new PropertyField[]{};
        } else {
            PropertyField[] ownedProperties = new PropertyField[counter];
            for (int i = 0; i < counter; i++){
                ownedProperties[i] = tempProperties[i];
            }
            return ownedProperties;

        }
    }

    public String[] getPlayerPropertyNames(Player player){
        PropertyField[] props = getPlayerProperties(player);
        String[] names = new String[props.length];
        for (int i = 0; i < props.length; i++){
            names[i] = props[i].getName();
        }

        return names;
    }

   public CompanyField[] getPlayerCompanies(Player player){
        CompanyField[] tempProperties = new CompanyField[Global.COLORED_PROPERTIES];

        int counter = 0;

        for (int i = 0; i < fields.length; i++){
            if (fields[i] instanceof CompanyField && ((CompanyField) fields[i]).getOwner() == player){
                tempProperties[counter] = (CompanyField) fields[i];
                counter++;
            }
        }

       if (counter == 0){
            return new CompanyField[]{};
        } else {
            CompanyField[] ownedProperties = new CompanyField[counter];
            for (int i = 0; i < counter; i++){
                ownedProperties[i] = tempProperties[i];
            }
            return ownedProperties;

        }
    }

    public PropertyField getPropertyFieldByName(String name){
        for (Field property : getFields()){
            if (property instanceof PropertyField){
                if (property.getName() == name){
                    return (PropertyField) property;
                }
            }
        }
        return null;
    }

    public CompanyField getCompanyFieldByName(String name){
        for (Field property : getFields()){
            if (property instanceof CompanyField){
                if (property.getName() == name){
                    return (CompanyField) property;
                }
            }
        }
        return null;
    }
}
