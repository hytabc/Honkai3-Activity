/**
 * @author hytabc
 * @date 2022/7/21
 */
public class glx_with_yd {

    public static void main(String[] args) {
        int A_win=0;
        int B_win=0;
        int fightNum=100000;
        for (int i=0;i<fightNum;i++){
            Player A = new Player("伊甸",100,16,12,16);
            Player B = new Player("格蕾修",100,16,11,18);
            if(fight(A,B)){
                A_win++;
            }else{
                B_win++;
            }
        }
        System.out.println("总战斗次数"+fightNum);
        //System.out.println("伊甸胜场为："+A_win);
        //System.out.println("格蕾修胜场为："+B_win);
        double A_w=((double)A_win/(double)fightNum)*100;//A胜率
        double B_w=((double)B_win/(double)fightNum)*100;//B胜率
        System.out.println("伊甸胜率为"+A_w+"%");
        System.out.println("格蕾修胜率为"+B_w+"%");
    }

    public static boolean fight(Player A,Player B){
        int count=0;
        int A_FirstAttack=0;
        int B_skillModelA=0;
        int A_win=0;
        int B_win=0;
        while(A.getHel()>0&&B.getHel()>0) {
            count++;
            //System.out.println("回合"+count+":");
            B_skillModelA=B_Shield(A,B,B_skillModelA);
            //A触发先手攻击
            if (A_FirstAttack == 1) {
                //System.out.println(A.getName()+"触发先手攻击");
                A_FirstAttack -= 1;
                //判断格蕾修护盾
                //A攻击
                if (A_Skill_A(count, A, B)) {
                    A_FirstAttack = 1;
                    //System.out.println(A.getName()+"发动技能成功,下回合将变为先手攻击。");
                } else {
                    A_DefaultAtk(A, B);
                    A_Skill_B(A, B);
                }
                //System.out.println(B.getName()+"血量为:"+B.getHel());
                if (B.getHel() <= 0) {
                    A_win++;
                    break;
                }

                B_skillModelA=B_Shield(A,B,B_skillModelA);
                //B攻击
                if(B_Skill_A(count,A,B)){
                    
                }else{
                    B_DefaultAtk(A,B);
                }
                B_Skill_B(A,B);
                //System.out.println(A.getName()+"血量为:"+A.getHel());
                if (A.getHel() <= 0) {
                    B_win++;
                    break;
                }
            } else {
                //B攻击
                if(B_Skill_A(count,A,B)){

                }else{
                    B_DefaultAtk(A,B);
                }
                B_Skill_B(A,B);
                //System.out.println(A.getName()+"血量为:"+A.getHel());
                if (A.getHel() <= 0) {
                    B_win++;
                    break;
                }
                //
                B_skillModelA=B_Shield(A,B,B_skillModelA);
                //A攻击
                if (A_Skill_A(count, A, B)) {
                    A_FirstAttack = 1;
                } else {
                    A_DefaultAtk(A, B);
                    A_Skill_B(A, B);
                }
                B_skillModelA=B_Shield(A,B,B_skillModelA);

                //System.out.println(B.getName()+"血量为:"+B.getHel());
                if (B.getHel() <= 0) {
                    A_win++;
                    break;
                }

            }
        }
        if(A_win==1)return true;
        else return false;
    }


    public static int randomInt(){
        double randomDouble=(Math.random()*100);
        //System.out.println("骰子触发:"+(int)Math.round(randomDouble));
        return (int)Math.round(randomDouble);
    }
    //A普通攻击
    public static void A_DefaultAtk(Player A,Player B){
        if(A.getAtk()>B.getDef()){
            B.setHel(B.getHel()-(A.getAtk()-B.getDef()));
            //System.out.println(A.getName()+"发动了普通攻击,对"+B.getName()+"造成了"+(A.getAtk()-B.getDef())+"点伤害");
        }
    }
    //A主动技能
    public static boolean A_Skill_A(int count,Player A,Player B){
        if(count%2==0){
            A.setAtk(A.getAtk()+4);
            //System.out.println(A.getName()+"发动技能，"+A.getName()+"攻击力现在为:"+A.getAtk());
            A_DefaultAtk(A,B);
            A_Skill_B(A,B);
            return true;
        }else{
            return false;
        }

    }
    //A被动技能
    public static void A_Skill_B(Player A,Player B){
        if(randomInt()>=50) {
            //System.out.println(A.getName()+"发动了被动技能，额外造成一次攻击");
            A_DefaultAtk(A, B);
        }
    }


    //B普通攻击
    public static void B_DefaultAtk(Player A,Player B){
        if(B.getAtk()>A.getDef()){
            A.setHel(A.getHel()-(B.getAtk()-A.getDef()));
            //System.out.println(B.getName()+"发动了普通攻击,对"+A.getName()+"造成了"+(B.getAtk()-A.getDef())+"点伤害");
        }
    }
    //B主动技能
    public static boolean B_Skill_A(int count,Player A,Player B){
        if(count%3==0){
            if(B.getSkillModel()>0){
                //System.out.println("格蕾修护盾未破，对伊甸造成"+(B.getDef()-A.getDef())+"点伤害");
                A.setHel(A.getHel()-(B.getDef()-A.getDef()));
            }
            //System.out.println("格蕾修发动技能获得15点护盾");
            B.setSkillModel(1);
            B.setShield(15);
            return true;
        }else{
            return false;
        }
    }
    //B护盾判定
    public static int B_Shield(Player A,Player B,int B_skillModelA){
        if(B.getShield()>0){
            B_skillModelA=B.getHel();
            B.setHel(B.getHel()+B.getShield());
            //System.out.println("格蕾修护盾叠加至血量,现在血量为"+B.getHel());
            B.setShield(0);
        }
        if(B.getHel()<=B_skillModelA){
            //System.out.print("格蕾修护盾已破");
            int temAtk=((int)(Math.round((B.getDef()*(Math.random()*2+2))))-A.getDef());
            A.setHel(A.getHel()-temAtk);
            //System.out.println("对伊甸造成"+temAtk+"点伤害");
            B_skillModelA=0;
            B.setSkillModel(0);
        }else if(B.getSkillModel()==1){
            //System.out.println("格蕾修护盾未破，所剩护盾量为"+(B.getHel()-B_skillModelA));
        }
        return B_skillModelA;
    }
    //B被动技能
    public static boolean B_Skill_B(Player A,Player B){
        if(randomInt()<=40){
            //System.out.print("格蕾修触发被动技能，现在的防御力为");
            B.setDef(B.getDef()+2);
            if(B.getDef()>21)B.setDef(21);
            //System.out.println(B.getDef());
            return true;
        }else{
            return false;
        }
    }
}
