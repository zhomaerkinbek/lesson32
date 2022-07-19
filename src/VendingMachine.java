import java.util.List;
import java.util.Map;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class VendingMachine {
    private final Map<String, Product> productList;
    private int money;
    private boolean work;

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

    public void closeMachine(){
        setWork(false);
    }

    public VendingMachine() {
        productList = Product.makeProducts(5).stream()
                .collect(toMap(Product::getId, product -> product));
        work = true;
    }

    public void addMoney(int money){
        setMoney(getMoney() + money);
    }
    public void deleteMoney(int money){
        setMoney(Math.max(getMoney() - money, 0));
    }

    public Map<String, Product> getProductList() {
        return productList;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
