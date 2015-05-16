/**
 * @author Justin Chang
 *
 * Attacks for -500 HP
 */

public class GodSword implements AttackItem {

    private Entity e, o;
    private String name = "God Sword";
    private String info = "Attacks with 500 HP (hehehehehehe).";
    private String useMsg = "You smote your enemy with the power of all things holy.";
    private String type = "attackable";
    private int damage = -500;
    private boolean useupable = false;

    public GodSword(Entity e, Entity o) {
        this.e = e;
        this.o = o;
    }

    public void execute() {
        o.setHP(damage);
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
