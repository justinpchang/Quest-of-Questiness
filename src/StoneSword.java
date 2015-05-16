/**
 * @author Justin Chang
 * @since 2015-05-08
 *
 * Attacks for -15 HP
 */

public class StoneSword implements AttackItem {

    private Entity e, o;
    private String name = "Stone Sword";
    private String info = "Attacks with 5 HP (with a slight multiplier based on melee stat).";
    private String useMsg = "You swung your stone sword!";
    private String type = "attackable";
    private int damage = -5;
    private boolean useupable = false;

    public StoneSword(Entity e, Entity o) {
        this.e = e;
        this.o = o;
    }

    public void execute() {
        o.setHP(damage * (e.getMelee() * 4 / 5));
        System.out.println(getUseMsg());
    }

    public Entity getOwner() {
        return e;
    }

    public Entity getOpponent() {
        return o;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getType() {
        return type;
    }

    public String getUseMsg() {
        return useMsg;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isUseupable() {
        return useupable;
    }

    public void changeOwner(Entity e) {
        this.e = e;
    }

    public void changeOpponent(Entity o) {
        this.o = o;
    }

}
