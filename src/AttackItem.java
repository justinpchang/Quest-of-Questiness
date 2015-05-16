/**
 * @author Justin Chang
 */

public interface AttackItem extends Item {

    Entity getOpponent();
    int getDamage();
    void changeOpponent(Entity e);

}
