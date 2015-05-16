/**
 * @author Justin Chang
 */

public interface Item {

    void execute();
    Entity getOwner();
    String getName();
    String getInfo();
    String getType();
    String getUseMsg();
    boolean isUseupable(); // find a better word...
    void changeOwner(Entity e);

}
