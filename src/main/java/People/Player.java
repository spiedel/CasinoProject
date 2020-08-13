package People;

public class Player extends Person{

    private double moneyInWallet;


    public Player(String name, int age, double startingMoney) {
        super(name, age);
        this.moneyInWallet = startingMoney;
    }

    public double getMoneyInWallet() {
        return moneyInWallet;
    }

    public void buyChips(){
        this.addChips((int) (moneyInWallet / 5));
        this.moneyInWallet = 0;
    }

    public void cashInChips(){
        moneyInWallet += getNumberOfChips() * 5;
        this.removeChips(getNumberOfChips());
    }


}
