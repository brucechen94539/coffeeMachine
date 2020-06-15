package machine;

import java.util.*;

public class CoffeeMachine {
    static int waterBase = 200;
    static int milkBase = 50;
    static int beansBase = 15;
    static int waterCap = 400;
    static int milkCap = 540;
    static int beansCap = 120;
    static int cupsCap = 9;
    static int moneyBank = 550;

    public static void main(String[] args) {
        /*System.out.println("The coffee machine has:");
        System.out.println("400 of water");
        System.out.println("540 of milk");
        System.out.println("120 of coffee beans");
        System.out.println("9 of disposable cups");
        System.out.println("$550 of money");*/


        Scanner sc = new Scanner(System.in);
        System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
        String action = sc.next();

        while (!action.equals("exit")) {
            switch (action) {
                case "buy":
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                    //int type = sc.nextInt();
                    Object type = sc.next();
                    if (type.equals("1") || type.equals("2") || type.equals("3")) {
                        dispenseCoffee(1, Integer.parseInt(type.toString()));
                    } else if (type.equals("back")) {
                        action = "buy";
                    } else {
                        System.out.println();
                        action = "exit";
                        return;
                    }
                    break;
                case "fill":
                    fillUp();
                    break;
                case  "take":
                    takeMoney();
                    break;
                case "remaining":
                    displayResults(waterCap, milkCap, beansCap, cupsCap, moneyBank);
                    break;
                default:
                    System.out.println("No, I can make only 0 cup(s) of coffee");
                    break;
            }
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            action = sc.next();
        }


        /*System.out.println("Write how many ml of water the coffee machine has: ");
        int waterCap = sc.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has: ");
        int milkCap = sc.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        int beansCap = sc.nextInt();*/


        /*System.out.println("Write how many cups of coffee you will need: ");
        int demandCups = sc.nextInt();
        int waterDemand = demandCups * waterBase;
        int milkDemand = demandCups * milkBase;
        int beanDemand = demandCups * beansBase;
        int totalPossible = totalCups(waterCap, milkCap, beansCap);

        if ((waterDemand > waterCap)
            || (milkDemand > milkCap)
            || (beanDemand > beansCap)) {
            System.out.println("No, I can make only " + totalPossible +" cup(s) of coffee");
        } else if ((waterDemand <= waterCap)
                && (milkDemand <= milkCap)
                && (beanDemand <= beansCap)) {
            int cupsLeftOver = totalPossible - demandCups;
            if (cupsLeftOver >= 1) {
                System.out.print("Yes, I can make that amount of coffee");
                System.out.println(" (and even " +cupsLeftOver +" more than that)");
            } else {
                System.out.println("Yes, I can make that amount of coffee");
            }
        }*/
        
    }

    static int totalCups(int waterCap, int milkCap, int beansCap) {
        int wCount = waterCap / waterBase;
        int mCount = milkCap / milkBase;
        int bCount = beansCap / beansBase;
        Set<Integer> col = new HashSet(Arrays.asList(wCount, mCount, bCount));
        return Collections.min(col);
    }

    static void dispenseCoffee(int unit, int type) {
        /*int waterLeft = waterCap - Coffee.valueOfType(type).waterDemand;
        int milkLeft = milkCap - Coffee.valueOfType(type).milkDemand;
        int beansLeft = beansCap - Coffee.valueOfType(type).beansDemand;
        int cupsLeft = cupsCap - unit;
        int moneyEarned = moneyBank + Coffee.valueOfType(type).dollarAmt;*/

        int waterDemand = Coffee.valueOfType(type).waterDemand;
        int milkDemand = Coffee.valueOfType(type).milkDemand;
        int beansDemand = Coffee.valueOfType(type).beansDemand;
        int totalPossible = totalCups(waterCap, milkCap, beansCap);
        int dollarAmt = Coffee.valueOfType(type).dollarAmt;
        int demandCups = 1;

        /*if ((waterDemand > waterCap)
                || (milkDemand > milkCap)
                || (beansDemand > beansCap)) {
            System.out.println("No, I can make only " + totalPossible +" cup(s) of coffee");
        } else if ((waterDemand <= waterCap)
                && (milkDemand <= milkCap)
                && (beansDemand <= beansCap)) {
            int cupsLeftOver = totalPossible - demandCups;
            if (cupsLeftOver >= 1) {
                System.out.print("Yes, I can make that amount of coffee");
                System.out.println(" (and even " +cupsLeftOver +" more than that)");
            } else {
                System.out.println("Yes, I can make that amount of coffee");
            }
        }*/

        if (waterDemand > waterCap) {
            System.out.println("Sorry, not enough water!");
        } else if (milkDemand > milkCap) {
            System.out.println("Sorry, not enough milk!");
        } else if (beansDemand > beansCap) {
            System.out.println("Sorry, not enough beans!");
        } else if ((waterDemand <= waterCap)
                && (milkDemand <= milkCap)
                && (beansDemand <= beansCap)) {
            System.out.println("I have enough resources, making you a coffee!\n");
            waterCap -= waterDemand;
            milkCap -= milkDemand;
            beansCap -= beansDemand;
            cupsCap -= unit;
            moneyBank += dollarAmt;
        }

        //displayResults(waterLeft, milkLeft, beansLeft, cupsLeft, moneyEarned);
    }

    static void takeMoney() {
        System.out.println("I gave you $" +moneyBank);
        moneyBank = 0;
        System.out.println();
        //displayResults(waterCap, milkCap, beansCap, cupsCap, moneyBank);
    }

    static void fillUp () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add: ");
        //int waterUpdated = sc.nextInt() + waterCap;
        waterCap += sc.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        //int milkUpdated = sc.nextInt() + milkCap;
        milkCap += sc.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        //int beansUpdated = sc.nextInt() + beansCap;
        beansCap += sc.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        //int cupsUpdated = sc.nextInt() + cupsCap;
        cupsCap += sc.nextInt();

        //displayResults(waterUpdated, milkUpdated, beansUpdated, cupsUpdated, moneyBank);
    }

    static void displayResults(int water, int milk, int beans, int cups, int money) {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println("$" + money + " of money");
        System.out.println();
    }

    public static enum Coffee {
        ESPRESSO("espresso", 1, 250, 0, 16, 4),
        LATTE("latte", 2, 350, 75, 20, 7 ),
        CAPPUCCINO("cappuccino", 3, 200, 100, 12, 6);

        private static final Map<String, Coffee> BY_LABEL = new HashMap<>();
        private static final Map<Integer, Coffee> BY_TYPE = new HashMap<>();
        private static final Map<Integer, Coffee> BY_WATER_DEMAND = new HashMap<>();
        private static final Map<Integer, Coffee> BY_MILK_DEMAND = new HashMap<>();
        private static final Map<Integer, Coffee> BY_BEANS_DEMAND = new HashMap<>();
        private static final Map<Integer, Coffee> BY_DOLLAR_AMT = new HashMap<>();

        static {
            for (Coffee e : values()) {
                BY_LABEL.put(e.label, e);
                BY_TYPE.put(e.type, e);
                BY_WATER_DEMAND.put(e.waterDemand, e);
                BY_MILK_DEMAND.put(e.milkDemand, e);
                BY_BEANS_DEMAND.put(e.beansDemand, e);
                BY_DOLLAR_AMT.put(e.dollarAmt, e);
            }
        }

        public final String label;
        public final int type;
        public final int waterDemand;
        public final int milkDemand;
        public final int beansDemand;
        public final int dollarAmt;

        private Coffee(String label, int type, int waterDemand, int milkDemand, int beansDemand, int dollarAmt) {
            this.label = label;
            this.type = type;
            this.waterDemand = waterDemand;
            this.milkDemand = milkDemand;
            this.beansDemand = beansDemand;
            this.dollarAmt = dollarAmt;
        }

        public static Coffee valueOfLabel(String label) {
            return BY_LABEL.get(label);
        }

        public static Coffee valueOfType(int type) {
            return BY_TYPE.get(type);
        }

        public static Coffee valueOfWaterDemand(int waterDemand) {
            return BY_WATER_DEMAND.get(waterDemand);
        }

        public static Coffee valueOfMilkDemand(int milkDemand) {
            return BY_MILK_DEMAND.get(milkDemand);
        }

        public static Coffee valueOfBeansDemand(int beansDemand) {
            return BY_BEANS_DEMAND.get(beansDemand);
        }

        public static Coffee valueOfDollarAmt(int dollarAmt) {
            return BY_DOLLAR_AMT.get(dollarAmt);
        }
    }

}

