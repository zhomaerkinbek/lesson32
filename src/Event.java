import java.util.Map;
import java.util.Scanner;

public enum Event {

    ADD_COIN("Закинуть еще монет"){
        @Override
        public void run(VendingMachine machine, String productId) {
            String choose;
            System.out.println("Выберите какую монетку положить:");
            var coins = Map.of("1", Coin.FIVE, "2", Coin.TEN, "3", Coin.TWENTY_FIVE);
            coins.forEach((number, coin) -> {
                System.out.printf("%s - %s сом\n", number, coin.getValue());
            });
            System.out.print("Введите номер: ");
            choose = new Scanner(System.in).nextLine().strip();
            if(coins.get(choose) != null) {
                machine.addMoney(coins.get(choose).getValue());
            }else {
                System.out.println("Такой монеты нет!");
            }
        }
    },
    BUY("купить"){
        @Override
        public void run(VendingMachine machine, String productId) {
            machine.deleteMoney(machine.getProductList().get(productId).getPrice());
            System.out.printf("Вы купили %s\n", machine.getProductList().get(productId).getName());
            machine.getProductList().remove(productId);

        }
    },
    QUIT("Выйти"){
        @Override
        public void run(VendingMachine machine, String productId) {
            machine.closeMachine();
        }
    };


    private String value;

    public String getValue() {
        return value;
    }

    Event(String value) {
        this.value = value;
    }


    public abstract void run(VendingMachine machine, String productId);
}
