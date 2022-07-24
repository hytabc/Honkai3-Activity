/**
 * @author hytabc
 * @date 2022/7/21
 */
public class yd_with_qj {
//    Player A = new Player("yd",100,16,12,16);
//    Player B = new Player("qj",100,23,9,26);

    public static void main(String[] args) {
        int A_win=0;
        int B_win=0;
        int fightNum=10000;
        for (int i=0;i<fightNum;i++){
            Player A = new Player("伊甸",100,16,12,16);
            Player B = new Player("千劫",100,23,9,26);
            if(fight(A,B)){
                A_win++;
            }else{
                B_win++;
            }
        }
        System.out.println("总战斗次数"+fightNum);
        double A_w=((double)A_win/(double)fightNum)*100;//A胜率
        double B_w=((double)B_win/(double)fightNum)*100;//B胜率
        System.out.println("A胜率为"+A_w+"%");
        System.out.println("B胜率为"+B_w+"%");
    }

    public static boolean fight(Player A,Player B){
        int count=0;
        int A_FirstAttack=0;
        int B_rest=1;
        int A_win=0;
        int B_win=0;
        while(A.getHel()>0&&B.getHel()>0){
            count++;
            //A攻击
            if(A_FirstAttack==1){
//                //System.out.println(A.getName()+"触发先手攻击");
                A_FirstAttack-=1;
                //A攻击

                if(A_Skill_A(count,A,B)){
                    A_FirstAttack=1;
//                    //System.out.println(A.getName()+"发动技能成功,下回合将变为先手攻击。");
                }
                else{
                    A_DefaultAtk(A,B);
                    A_Skill_B(A,B);
                }
                //System.out.println(B.getName()+"血量为:"+B.getHel());
                if(B.getHel()<=0){
                    A_win++;
                    break;
                }
                //B攻击
                B_Skill_B(A,B);
                if(B_rest!=0){
                    if(B_Skill_A(count,A,B)){
                        B_rest=0;
                    }else{
                        B_DefaultAtk(A,B);
                    }
                }else{
                    //System.out.println(B.getName()+"进入休息回合");
                    B_rest=1;
                }
                //System.out.println(A.getName()+"血量为:"+A.getHel());
                if(A.getHel()<=0){
                    B_win++;
                    break;
                }
            }else{
                //B攻击
                B_Skill_B(A,B);
                if(B_rest!=0){
                    if(B_Skill_A(count,A,B)){
                        B_rest=0;
                    }else{
                        B_DefaultAtk(A,B);
                    }
                }else{
                    //System.out.println(B.getName()+"进入休息回合");
                    B_rest=1;
                }
                //System.out.println(A.getName()+"血量为:"+A.getHel());
                if(A.getHel()<=0){
                    B_win++;
                    break;
                }
                //A攻击
                if(A_Skill_A(count,A,B)){
                    A_FirstAttack=1;
                }
                else{
                    A_DefaultAtk(A,B);
                    A_Skill_B(A,B);
                }
                //System.out.println(B.getName()+"血量为:"+B.getHel());
                if(B.getHel()<=0){
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
    public static boolean judgeMAX(int speedA,int speedB){
        if(speedA>speedB){
            return true;
        }else{
            return false;
        }
    }
    public static void A_DefaultAtk(Player A,Player B){
        if(A.getAtk()>B.getDef()){
            B.setHel(B.getHel()-(A.getAtk()-B.getDef()));
            //System.out.println(A.getName()+"发动了普通攻击,对"+B.getName()+"+造成了"+(A.getAtk()-B.getDef())+"点伤害");
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
            //System.out.println(B.getName()+"对"+A.getName()+"造成了"+(B.getAtk()-A.getDef())+"点伤害");
        }
    }
    public static boolean B_Skill_A(int count,Player A,Player B){
        if(count%3==0){
            if(B.getHel()>=11){
                B.setHel(B.getHel()-10);
                A.setHel(A.getHel()-(45-A.getDef())-(int)Math.round(Math.random()*19+1));
                //System.out.println(B.getName()+"发动了技能,对"+A.getName()+"造成了"+((45-A.getDef())+(int)Math.round(Math.random()*19+1))+"点伤害");
            }else{
                //System.out.println(B.getName()+"血量不足发动技能");
            }
            return true;
        }else{
            return false;
        }
    }
    public static void B_Skill_B(Player A,Player B){
        B.setAtk(23+(100-B.getHel())/5);
        //System.out.println(B.getName()+"发动了被动技能，现在"+B.getName()+"攻击力为："+B.getAtk());
    }
}
