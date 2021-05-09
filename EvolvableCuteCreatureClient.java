public class EvolvableCuteCreatureClient {
    public static void main(String[] args) {

        System.out.println("\n Test - Evolvable creature against regular creature.\n");

        CuteCreature BMO = new CuteCreature("BMO", 35, 18, 10, 210, false);
        EvolvableCuteCreature Yollee = new EvolvableCuteCreature("Yollee", 50, 34, 8, 210, true, 2, "Yolitta");

        System.out.println(BMO);
        System.out.println(" ");
        System.out.println(Yollee);
        System.out.println(" ");

        while (BMO.getHitPoints() > 0 && Yollee.getHitPoints() > 0) {

            if (Yollee.getHitPoints() == 0 || BMO.getHitPoints() == 0) {
                break;
            }

            BMO.attack(Yollee);
            System.out.print(" ");

            if (Yollee.getHitPoints() == 0 || BMO.getHitPoints() == 0) {
                break;
            }

            System.out.println(" ");
            Yollee.specialAttack(BMO);
            System.out.println(" ");
        }

        System.out.println(BMO);
        System.out.println(" ");
        System.out.println(Yollee);


        System.out.println("\nTest - Evolvable creature against Evolvable creature with same type and not yet evolved.\n");
        EvolvableCuteCreature Stormi = new EvolvableCuteCreature("" + "Stormi", 56, 18, 3, 300, true, 2, "StormBrew");

        System.out.println(Stormi);
        System.out.println(" ");
        System.out.println(Yollee);

        Stormi.specialAttack(Yollee);
        System.out.println(" ");

        System.out.println(" ");
        Yollee.specialAttack(Stormi);
        System.out.println(" ");

        System.out.println(Stormi);
        System.out.println(" ");
        System.out.println(Yollee);

        System.out.println("\n Test - Game between two evolvable creatures that have not evolved yet.\n");
        EvolvableCuteCreature Mino = new EvolvableCuteCreature("Mino", 50, 34, 8, 210, true, 2, "Montaru");
        EvolvableCuteCreature Rhino = new EvolvableCuteCreature("Rhino", 40, 24, 10, 210, true, 2, "Rhinotti");

        System.out.println(Mino);
        System.out.println(" ");
        System.out.println(Rhino);

        while (Mino.getHitPoints() > 0 && Rhino.getHitPoints() > 0) {

            if (Mino.getHitPoints() <= 0 || Rhino.getHitPoints() <= 0) {
                break;
            }

            Mino.specialAttack(Rhino);
            System.out.println(" ");

            if (Rhino.getHitPoints() == 0 || Mino.getHitPoints() == 0){
                break;
            }

            System.out.println(" ");
            Rhino.specialAttack(Mino);
            System.out.println(" ");
        }
        System.out.println(Mino);
        System.out.println(" ");
        System.out.println(Rhino);


        System.out.println("\nTest - Evolvable Creature against Evolvable Creature with vulnerable type.\n"); //

        System.out.println(Mino);
        System.out.println(" ");
        System.out.println(Yollee);

        Mino.specialAttack(Yollee);
        System.out.println(" ");

        System.out.println(" ");
        Yollee.specialAttack(Mino);
        System.out.println(" ");

        System.out.println(Mino);
        System.out.println(" ");
        System.out.println(Yollee);

        EvolvableCuteCreature Gerald = new EvolvableCuteCreature("Gerald", 50, 34, 8, 210, true, 2, "Geraldine");
        EvolvableCuteCreature Casper = new EvolvableCuteCreature("Casper", 40, 24, 10, 210, true, 2, "Casperissi");

        System.out.println("New Game between " + Gerald.getSpecies() + " and " + Casper.getSpecies());

        System.out.println(Gerald);
        System.out.println(" ");
        System.out.println(Casper);

        while (Gerald.getHitPoints() > 0 && Casper.getHitPoints() > 0) {

            if (Gerald.getHitPoints() <= 0 || Casper.getHitPoints() <= 0) {
                break;
            }

            Gerald.specialAttack(Casper);
            System.out.println(" ");

            if (Casper.getHitPoints() == 0 || Gerald.getHitPoints() == 0){
                break;
            }

            System.out.println(" ");
            Yollee.specialAttack(Gerald);
            System.out.println(" ");
        }
        System.out.println(Gerald);
        System.out.println(" ");
        System.out.println(Casper);

        // Evolvable creature against resistent type
        System.out.println("\nTest - Evolvable creature against evolvable creature with resistant type.\n");
        System.out.println(Gerald);
        System.out.println(" ");
        System.out.println(Mino);

        Gerald.specialAttack(Mino);
        System.out.println(" ");

        System.out.println(" ");
        Mino.specialAttack(Gerald);
        System.out.println(" ");

        System.out.println(Gerald);
        System.out.println(" ");
        System.out.println(Mino);

        System.out.println("\nTest - Evolvable creature against evolvable creature with neither resistant, vulnerable, same type\n");
        System.out.println(Gerald);
        System.out.println(" ");
        System.out.println(Yollee);

        Gerald.specialAttack(Yollee);
        System.out.println(" ");

        System.out.println(" ");
        Yollee.specialAttack(Gerald);
        System.out.println(" ");

        System.out.println(Gerald);
        System.out.println(" ");
        System.out.println(Yollee);
    }
}