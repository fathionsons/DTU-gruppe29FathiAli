package Model.Fields;

import Model.Player;
import gui_fields.GUI_Brewery;
import gui_fields.GUI_Field;
import gui_fields.GUI_Shipping;
import gui_resources.Attrs;

import java.awt.*;

/**
 * class of PropertyField
 */
public class CompanyField extends Field {

    private Color color;
    private boolean isShipping;
    private int price;
    private int rent;
    private Player owner;

   public CompanyField(String name, String subText, String description, int price, Color color, boolean isShipping){
        super(name, subText, description);
        this.price = price;
        this.rent = price / 2; // SKAL ÆNDRET SENERE !!!
        this.color = color;
        this.isShipping = isShipping;
    }

    @Override
    public void fieldAction(Player player){
        if (this.isOwned()){
            payToPlayerLogic(player);
        } else {
            player.setLastAction(player.getLastAction() + "\n - Er landet på " +
                    this.getName());

            System.out.println("[INFO] " + player.getName() + " er landet på " +
                    this.getName());
        }
    }

    private void payToPlayerLogic(Player player){
        if (!owner.isBankrupt() && player != owner){
            player.setLastAction(player.getLastAction() + "\n - Har betalt " +
                    this.getRent() + " kr. til " +
                    this.getOwner().getName() + ".");

            System.out.println("[INFO] " + player.getName() + " har betalt " +
                    this.getRent() + " kr. til " +
                    this.getOwner().getName() + ".");
            payToPlayerField(player);
        }
    }

    private void payToPlayerField(Player player){
        Player owner = this.getOwner();
        int payment = this.getRent();
        player.addMoney(-payment);
        owner.addMoney(payment);
    }

    public void buyField(Player player){
        if (!isOwned()){
            player.setLastAction(player.getLastAction() + "\n - Har købt " +
                    this.getName() + " for " +
                    this.getPrice() + " kr.");

            System.out.println("[INFO] " + player.getName() + " har købt " +
                    this.getName() + " for " +
                    this.getPrice() + " kr.");

            int payment = this.getPrice();
            player.addMoney(-payment);
            this.setOwner(player);
        }
    }

    @Override
    public String getName(){
        return super.getName();
    }

    @Override
    public String getSubText(){
        return super.getSubText();
    }

    @Override
    public String getDescription(){
        return super.getDescription();
    }

    public boolean isOwned(){
        Player owner = this.getOwner();
        return owner != null;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public boolean isShipping(){
        return isShipping;
    }

    public void setShipping(boolean shipping){
        isShipping = shipping;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public Player getOwner(){
        return owner;
    }

    public void setOwner(Player owner){
        this.owner = owner;
    }

    public int getRent(){
        return rent;
    }

    public void setRent(int rent){
        this.rent = rent;
    }
}
