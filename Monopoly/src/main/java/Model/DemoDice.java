package Model;

/* 3 players dice */

public class DemoDice extends Dice{
    private int result;
    private int[] pair;
    private int sides;
    private int amount;
    private int[][] demoResults;
    private int demoIndex = 0;


    // #----------Constructor----------#
    protected DemoDice() {
        this.sides = Global.DICE_SIDES;
        this.amount = Global.DICE_AMOUNT;
        this.demoResults = new int[][]{
                // Her sættes nogle terninge resultater i følgende ordre

                // Spiller 1s 2 terninger
                // Spiller 2s 2 terninger
                // Spiller 3s 2 terninger
                // forfra...
                new int[]{6,6},
                new int[]{1,1},
                new int[]{1,1},

                new int[]{6,6},
                new int[]{5,5},
                new int[]{5,5},

                new int[]{3,3},
                new int[]{5,5},
                new int[]{5,5},

                new int[]{2,3},
                new int[]{5,5},
                new int[]{5,5},

                new int[]{2,3},
                new int[]{5,5},
                new int[]{5,5},

                new int[]{2,3},
                new int[]{5,5},
                new int[]{5,5},

                new int[]{2,3},
                new int[]{5,5},
                new int[]{5,5},

        };
    }

    public int setAndGetResult() { 
    	
        if (demoIndex < demoResults.length) {
            this.result = demoResults[demoIndex][0] + demoResults[demoIndex][1];
            this.setPair(demoResults[demoIndex]);

            demoIndex++;

            return this.result;

        } else {
            int[] diceThrow = new int[amount + 1];
            int sum = 0;
            for (int i = 0; i < amount; i++) {
                float _random1 = (float) Math.random();    // 0-1 float
                int _random2 = (int) (_random1 * sides);   // 0-5 integer
                int random = _random2 + 1;                 // 1-6 integer
                diceThrow[i] = random;
                sum += random;
            }

            this.result = sum;
            setPair(diceThrow);
            return this.result;
        }
    }

    public int[] getPair() {
        return pair;
    }

    public void setPair(int[] pair) {
        this.pair = pair;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
