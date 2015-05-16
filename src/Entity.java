import java.util.ArrayList;

/**
 * @author  Justin Chang
 */

public class Entity {

    // general stats
    private int hp, mp, xp;
    private Inventory inv;
    private String role;
    private int skillpoints;
    // skill stats
    private int level, melee, ranged, magic, defense;

    // constructor
    public Entity() { // player constructor
        hp = 20;
        mp = 10;
        xp = 0;
        level = 1;
        melee = 0;
        ranged = 0;
        magic = 0;
        defense = 0;
        inv = new Inventory();
        skillpoints = 0;
    }

    public Entity(int level) { // enemy constructor
        this.level = level;
        adjustStats(level);
        role = getRole();
    }

    // adjust starting stats for enemies when they are initialized
    public void adjustStats(int level) {
        hp = (level - 1) * 5 + (int) (Math.random() * 7 + 18);
        // allocate skill points
        int skillpoints = level * 2 - 2;
        int rand = (int) (Math.random() * 4);
        switch (rand) {
            // soldier
            case 0:
                melee = skillpoints / 2 + (int) (Math.random() * 5 - 2);
                skillpoints -= melee;
                ranged = skillpoints / 3 + (int) (Math.random() * 5 - 2);
                skillpoints -= ranged;
                magic = skillpoints / 2 + (int) (Math.random() * 3 - 1);
                skillpoints -= magic;
                defense = skillpoints;
                break;
            // ranger
            case 1:
                ranged = skillpoints / 2 + (int) (Math.random() * 5 - 2);
                skillpoints -= melee;
                melee = skillpoints / 3 + (int) (Math.random() * 5 - 2);
                skillpoints -= ranged;
                magic = skillpoints / 2 + (int) (Math.random() * 3 - 1);
                skillpoints -= magic;
                defense = skillpoints;
                break;
            // mage
            case 2:
                magic = skillpoints / 2 + (int) (Math.random() * 5 - 2);
                skillpoints -= melee;
                ranged = skillpoints / 3 + (int) (Math.random() * 5 - 2);
                skillpoints -= ranged;
                melee = skillpoints / 2 + (int) (Math.random() * 3 - 1);
                skillpoints -= magic;
                defense = skillpoints;
                break;
            // tank
            case 3:
                defense = skillpoints / 2 + (int) (Math.random() * 5 - 2);
                skillpoints -= melee;
                ranged = skillpoints / 3 + (int) (Math.random() * 5 - 2);
                skillpoints -= ranged;
                magic = skillpoints / 2 + (int) (Math.random() * 3 - 1);
                skillpoints -= magic;
                melee = skillpoints;
                break;
        }
        // make sure no stats are negative
        if(melee < 0)
            melee = 0;
        if(ranged < 0)
            ranged = 0;
        if(magic < 0)
            magic = 0;
        if(defense < 0)
            defense = 0;

        mp = (level - 1) * 2 + magic * level - level / 2 + (int) (Math.random() * 7 + 6);
    }

    // inventory accessors
    public boolean hasItem(Item item) {
        return inv.hasItem(item);
    }
    public void useItem(Item item) {
        if (inv.hasItem(item)) {
            item.execute();
            if (item.isUseupable()) removeItem(item);
        }
    }
    public int howManyOf(Item item) {
        return inv.howManyOf(item);
    }

    // inventory mutators
    public ArrayList<Item> getItems() {
        return inv.getItems();
    }
    public void addItem(Item item) {
        inv.addItem(item);
    }
    public void addItem(Item item, int count) {
        inv.addItem(item, count);
    }
    public void removeItem(Item item) {
        inv.removeItem(item);
    }
    public void removeItem(Item item, int count) {
        inv.removeItem(item, count);
    }

    // stat accessors
    public String getRole() {
        int[] skills = {melee, ranged, magic, defense};
        int maxIndex = 0;
        String roleIs = "";
        for (int i = 1; i < skills.length; i++)
            if (skills[i] > skills[maxIndex])
                maxIndex = i;
        switch (maxIndex) {
            case 0:
                roleIs = "soldier";
                break;
            case 1:
                roleIs = "ranger";
                break;
            case 2:
                roleIs = "mage";
                break;
            case 3:
                roleIs = "tank";
                break;
        }
        return roleIs;
    }
    public int getHP() {
        return hp;
    }
    public boolean isDead() {
        if (hp <= 0) {
            hp = 0;
            return true;
        }
        return false;
    }
    public int getMP() {
        return mp;
    }
    public int getXP() {
        return xp;
    }
    public int getLvl() {
        return level;
    }
    public int getMelee() {
        return melee;
    }
    public int getRanged() {
        return ranged;
    }
    public int getMagic() {
        return magic;
    }
    public int getDefense() {
        return defense;
    }
    public int getSkillpoints() {
        return skillpoints;
    }

    // stat mutators
    public void setHP(int change) {
        hp += change;
    }
    public void resetHP(int base) {
        hp = base;
    }
    public void setMP(int change) {
        mp += change;
    }
    public void addXP(Entity enemy) {
        xp += enemy.getLvl() * 10;
        // check if reached next level
        levelUp();
    }
    public void levelUp() {
        int nextLevel = level * 13;
        if(xp >= nextLevel) {
            level++;
            xp = 0;
            skillpoints += 2;
            hp = 20 + (level - 1) * 5;
        }
    }
    public void setMelee(int change) {
        melee += change;
    }
    public void setRanged(int change) {
        ranged += change;
    }
    public void setMagic(int change) {
        magic += change;
    }
    public void setDefense(int change) {
        defense += change;
    }

}
