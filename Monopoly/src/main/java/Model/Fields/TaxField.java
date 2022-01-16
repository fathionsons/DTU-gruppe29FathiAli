package Model.Fields;

import Model.Player;

public class TaxField extends Field {

    private int tax;
    private boolean choice;

    public TaxField(String name, String subText, String description, int tax, boolean choice){
        super(name, subText, description);
        this.tax = tax;
        this.choice = choice;
    }

    @Override
    public void fieldAction(Player player){
        player.setLastAction(player.getLastAction() + "\n - Er landet på et beskatningsfelt.");

        System.out.println("[INFO] " + player.getName() + "Er landet på et beskatningsfelt.");
        if (!this.isChoice()){

            payTax(player);

            player.setLastAction(player.getLastAction() + "\n - Har betalt " +
                    this.getTax() + " kr. i ekstraordinær statsskat.");
            System.out.println("[INFO] " + player.getName() + " har betalt " +
                    this.getTax() + " kr. i ekstraordinær statsskat.");
        }
    }

    private void payTax(Player player){
        int payment = this.getTax();
        player.addMoney(-payment);
    }

    public int getTax(){
        return tax;
    }

    public void setTax(int tax){
        this.tax = tax;
    }

    public boolean isChoice(){
        return choice;
    }

    public void setChoice(boolean choice){
        this.choice = choice;
    }
}
