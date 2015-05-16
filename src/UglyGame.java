import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Justin Chang
 * @since 2015-05-08
 *
 * TODO:
 * -inventory for enemies
 * -add commands: cast
 *
 * Would Like to Fix:
 * -math for adjustStats()
 * -math for levelling
 * -difficulty
 */
public class UglyGame {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String phrase, command, object, oldCommand="", oldObject="";
        Item curItem = null;
        boolean newEnemy = true;

        boolean playing = true;

        Entity player = new Entity();
        Entity enemy = new Entity(player.getLvl());

        // items----------------------------------------
        ArrayList<Item> items = new ArrayList<Item>();
        HealthPot hpot1 = new HealthPot(player);
        HealthPot hpot2 = new HealthPot(enemy);
        StoneSword stonesw1 = new StoneSword(player, enemy);
        StoneSword stonesw2 = new StoneSword(enemy, player);
        GodSword godsw1 = new GodSword(player, enemy);
        GodSword godsw2 = new GodSword(enemy, player);

        items.add(hpot1);
        items.add(hpot2);
        items.add(stonesw1);
        items.add(stonesw2);
        items.add(godsw1);
        items.add(godsw2);
        // end items------------------------------------

        // print stats of both entities
        System.out.println("PLAYER:");
        printStats(player);
        System.out.println("\nENEMY:");
        printStats(enemy);

        player.addItem(godsw1);

        // main game loop
        while(playing) {

            // new enemy (need to reset owner and opponent)
            if(newEnemy) {
                hpot1.changeOwner(player);
                hpot2.changeOwner(enemy);
                stonesw1.changeOwner(player);
                stonesw1.changeOpponent(enemy);
                stonesw2.changeOwner(enemy);
                stonesw2.changeOpponent(player);
                godsw1.changeOwner(player);
                godsw1.changeOpponent(enemy);
                godsw2.changeOwner(enemy);
                godsw2.changeOpponent(player);
                newEnemy = false;
            }

            // get input
            System.out.print(" > ");
            phrase = input.nextLine();
            System.out.println();

            // process input
            command = parse(phrase)[0];
            object = parse(phrase)[1];

            // check if it's the repeat command "'"
            if(command.equals("'")) {
                command = oldCommand;
                object = oldObject;
            }
            // execute input
            switch(command) {
                case "attack with":
                    for(Item i : items) {
                        if(i.getName().equals(object) && i.getOwner() == player) {
                            if(i.getType().equals("attackable")) {
                                curItem = i;
                            } else {
                                System.out.println("That item is not attackable!");
                                break;
                            }
                        }
                    }
                    player.useItem(curItem);
                    break;
                case "drink":
                    for(Item i : items) {
                        if(i.getName().equals(object) && i.getOwner() == player) {
                            if(i.getType().equals("drinkable")) {
                                curItem = i;
                            } else {
                                System.out.println("That item is not drinkable!");
                                break;
                            }
                        }
                    }
                    player.useItem(curItem);
                    break;
                case "inv":
                    System.out.println("Inventory:");
                    for(int i = 0; i < player.getItems().size(); i++) {
                        System.out.println(player.getItems().get(i).getName() + " - " + player.howManyOf(player.getItems().get(i)));
                    }
                    System.out.println("");
                    break;
                case "look":
                    for(Item i : items) {
                        if(i.getName().equals(object))
                            curItem = i;
                    }
                    System.out.println("Information on " + curItem.getName() + ": ");
                    System.out.println(curItem.getInfo());
                    break;
            }

            // store the command just given
            oldCommand = command;
            oldObject = object;

            // adds a block for the next execution
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            // set health to 0 if dead (no negatives!)
            if(player.isDead()) ;
            if(enemy.isDead()) ;

            // print stats of both entities
            System.out.println("PLAYER:");
            printStats(player);
            System.out.println("\nENEMY:");
            printStats(enemy);

            // print who has won
            if(player.isDead()) {
                System.out.println("You have died! Better luck next time.");
                break;
            } else if(enemy.isDead()) {
                System.out.println("\n\n\n\nYou have won the battle! Starting a new one...");
                player.addXP(enemy);
                enemy = new Entity(player.getLvl());

                // print stats of both entities
                System.out.println("\n\n\nPLAYER:");
                printStats(player);
                System.out.println("\nENEMY:");
                printStats(enemy);

                // print if there are skill points available
                if(player.getSkillpoints() > 0) System.out.println("You have " + player.getSkillpoints() + " skill points available!");

                newEnemy = true;
            }

        } // end game loop


    }

    public static String[] parse(String line) {
        String phrase = line;
        if(phrase.indexOf(' ') < 0) {
            String[] sa = {phrase, null};
            return sa;
        }
        String command = phrase.substring(0, phrase.indexOf(' '));
        if(command.toLowerCase().equals("attack") && phrase.length() > 11)
            command = phrase.substring(0, 11);
        String object = phrase.substring(command.length()+1);
        String[] sa = {command, object};
        return sa;
    }
    public static void printStats(Entity e) {
        System.out.println("HP: " + e.getHP() + " MP: " + e.getMP() + " XP: " + e.getXP() + " Level: " + e.getLvl());
        System.out.println("Melee: " + e.getMelee() + " Ranged: " + e.getRanged() + " Magic: " + e.getMagic() + " Defense: " + e.getDefense() + "\n");
    }
}
