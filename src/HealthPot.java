/**
 * @author Justin Chang
 *
 * Gives immediate +25HP
 */

public class HealthPot implements Item {

    private Entity e;
    private String name = "Health Potion";
    private String info = "Adds 25 HP to the user's health.";
    private String useMsg = "You drank a health potion for +25 HP!";
    private String type = "drinkable";
    private boolean useupable = true;

    public HealthPot(Entity e) {
        this.e = e;
    }

    public void execute() {
        e.setHP(25);
        System.out.println(getUseMsg());
    }

    public Entity getOwner() {
        return e;
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

    public boolean isUseupable() {
        return useupable;
    }

    public void changeOwner(Entity e) {
        this.e = e;
    }

}
