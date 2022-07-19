import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Main {

    static VendingMachine machine = new VendingMachine();
    static Scanner console = new Scanner(System.in);
    static Map<String, Event> eventMap = new HashMap<>();

    static {
        eventMap.put("a", Event.ADD_COIN);
        eventMap.put("q", Event.QUIT);
    }

    public static void main(String[] args){
        while (machine.isWork()) {
            if(machine.getProductList().size() > 0){
                System.out.println("В автомате доступны:");
                machine.getProductList().forEach((k, v) -> {
                    System.out.printf("[%d] - %s\n", v.getPrice(), v.getName());
                });
                System.out.println("Монет на сумму - " + machine.getMoney());
                events();
            }else {
                System.out.println("В автомате отсутствуют товары, приходите завтра!");
                machine.closeMachine();
            }
        }
    }


    public static void events(){
        String action;
        System.out.println("Что хотите сделать:");
        System.out.printf("a - %s\n", eventMap.get("a").getValue());

        machine.getProductList().entrySet()
                .stream()
                .filter(e -> e.getValue().getPrice() <= machine.getMoney())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue))
                        .forEach((k, v) -> {
                            eventMap.put(k, Event.BUY);
                        });
        eventMap.entrySet().removeIf(entry -> machine.getProductList().get(entry.getKey()) != null && machine.getProductList().get(entry.getKey()).getPrice() > machine.getMoney());
        eventMap.entrySet()
                .stream()
                .filter(e -> !e.getKey().equalsIgnoreCase("a") & !e.getKey().equalsIgnoreCase("q"))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue))
                .forEach((k, v) -> {
                    System.out.printf("%s - купить '%s'\n", k, machine.getProductList().get(k).getName());
                });

        System.out.printf("q - %s\n", eventMap.get("q").getValue());
        System.out.print(">? ");
        action = console.nextLine();
        if(eventMap.get(action) != null) {
            eventMap.get(action).run(machine, action);
            if(!action.equalsIgnoreCase("a") & !action.equalsIgnoreCase("q")){
                eventMap.remove(action);
            }
        }else {
            System.out.println("Такого действия/товара нету!");
        }

    }
}
