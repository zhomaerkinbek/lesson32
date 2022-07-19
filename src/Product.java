import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;

public class Product {
    private String id;
    private String name;
    private static final Random rnd = new Random();
    private int price;
    private static final List<String> usedId = new ArrayList<>();
    private static final List<String> listId = List.of("b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "v", "w", "x", "y", "z");

    private static final List<String> usedNames = new ArrayList<>();
    private static final List<String> names = List.of("Вода", "Скитлс", "Сникерс", "Энергетик", "Батончик", "Сэндвич", "Бананочипсы", "Содовая", "Пепси", "Макси чай", "Баунти", "Твикс");

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product() {
        id = getRandomId(listId.get(rnd.nextInt(listId.size())));
        name = getRandomName(names.get(rnd.nextInt(names.size())));
        price = getRandomPrice();
    }

    public static List<Product> makeProducts(int amount) {
        return Stream.generate(Product::new)
                .limit(amount)
                .sorted(comparing(Product::getPrice).reversed())
                .collect(toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private String getRandomName(String name){
        if(usedNames.contains(name)){
            return getRandomName(names.get(rnd.nextInt(names.size())));
        }
        usedNames.add(name);
        return name;
    }

    private String getRandomId(String id){
        if(usedId.contains(id)){
            return getRandomId(listId.get(rnd.nextInt(listId.size())));
        }
        usedId.add(id);
        return id;
    }
    private int getRandomPrice(){
        int value = rnd.nextInt(226) + 25;
        if(value % 5 == 0){
            return value;
        }
        return getRandomPrice();

    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
