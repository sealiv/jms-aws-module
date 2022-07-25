package org.aleks4ay.jms.sender;

import org.aleks4ay.jms.model.*;

import java.util.Scanner;

public class ConsoleWorker {

    public void run(JmsSender jmsSender) {
        boolean hasNewOrder = true;
        Scanner scanner = new Scanner(System.in);
        while (hasNewOrder) {
            Order order = getOrderFromConsole(scanner);
            jmsSender.send(order);

            System.out.println(System.lineSeparator() + "Create new " + order + System.lineSeparator());
            hasNewOrder = checkExit(scanner);
        }
        scanner.close();
    }

    private boolean checkExit(Scanner scanner) {
        System.out.println("Press 'Enter' if you need to exit or press any key to continue: ");
        String next = scanner.nextLine();
        if (next.equals("")) {
            System.out.println("Exit");
            return false;
        }
        return true;
    }

    private Order getOrderFromConsole(Scanner scanner) {
        System.out.println("Input your name: ");
        String name = scanner.nextLine();
        User user = new User(name);

        System.out.println("Choose type of goods, 1 - liquid; 2 - countable item: ");
        int type = scanner.nextInt();

        Order order;
        if (type == 1) {
            System.out.println("Input volume of liquid: (format 2.2)");
            double volume = scanner.nextDouble();
            Liquid liquid = new Liquid(volume);
            order = new Order(liquid, user);
        } else {
            System.out.println("Input number of goods: ");
            int quantity = scanner.nextInt();
            CounterItem counterItem = new CounterItem(quantity);
            order = new Order(counterItem, user);
        }
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        return order;
    }
}
