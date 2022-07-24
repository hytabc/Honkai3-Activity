/**
 * @author hytabc
 * @date 2022/7/21
 */
public class Player {
    private String name;
    private int hel;
    private int atk;
    private int def;
    private int speed;
    private int skillModel=0;
    private int Shield=0;

    public int getShield() {
        return Shield;
    }

    public void setShield(int shield) {
        Shield = shield;
    }

    public int getSkillModel() {
        return skillModel;
    }

    public void setSkillModel(int skillModel) {
        this.skillModel = skillModel;
    }

    public Player(String name, int hel, int atk, int def, int speed) {
        this.name = name;
        this.hel = hel;
        this.atk = atk;
        this.def = def;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHel() {
        return hel;
    }

    public void setHel(int hel) {
        this.hel = hel;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
