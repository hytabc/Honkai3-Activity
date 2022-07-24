/**
 * @author hytabc
 * @date 2022/7/21
 */
public class h_with_abny {

    public static void main(String[] args) {
        int A_win=0;
        int B_win=0;
        int fightNum=100000;
        for (int i=0;i<fightNum;i++){
            Player A = new Player("阿波尼亚",100,21,10,30);
            Player B = new Player("华",100,21,12,15);
            if(fight(A,B)){
                A_win++;
            }else{
                B_win++;
            }
        }
        System.out.println("总战斗次数"+fightNum);
        System.out.println("阿波尼亚胜场为："+A_win);
        System.out.println("华胜场为："+B_win);
        double A_w=((double)A_win/(double)fightNum)*100;//A胜率
        double B_w=((double)B_win/(double)fightNum)*100;//B胜率
        System.out.println("阿波尼亚胜率为"+A_w+"%");
        System.out.println("华胜率为"+B_w+"%");
    }

    public static boolean fight(Player A,Player B){
        int count=0;
        int A_FirstAttack=0;
        int B_rest=0;
        int A_win=0;
        int B_win=0;
        while(A.getHel()>0&&B.getHel()>0){
            count++;
            //System.out.println("回合"+count+":");
            //A攻击
            if(A_Skill_A(count,A,B)){
                B_rest=1;
            }else{
                A_DefaultAtk(A,B);
            }
            A_Skill_B(A,B);
            //System.out.println(B.getName()+"血量为:"+B.getHel());
            if(B.getHel()<=0){
                A_win++;
                break;
            }
            //B回合判定
            if(B_rest==1){
                //System.out.println(B.getName()+"本回合被封印");
                B_rest=0;
            }else{
                if(B.getSkillModel()==1){
                    A.setDef(A.getDef()-3);
                    B.setSkillModel(0);
                    //System.out.println("华蓄力状态结束");
                }
                //B攻击
                if(A.getSkillModel()==1){
                    //System.out.println(B.getName()+"被沉默，无法使用技能");
                    B_DefaultAtk(A,B);
                    A.setSkillModel(0);
                }else if(B_Skill_A(count,A,B)){
                    A.setDef(A.getDef()+3);
                }else{
                    B_DefaultAtk(A,B);
                }
                //System.out.println(A.getName()+"血量为:"+A.getHel());
                if(A.getHel()<=0){
                    B_win++;
                    break;
                }
            }
            //System.out.println("回合结束");
            //System.out.println(A.getName()+"剩余血量为"+A.getHel());
            //System.out.println(B.getName()+"剩余血量为"+B.getHel());
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
            B.setHel(B.getHel()-B_skill_B(A.getAtk()-B.getDef()));
            //System.out.println(A.getName()+"发动了普通攻击,对"+B.getName()+"造成了"+B_skill_B((A.getAtk()-B.getDef()))+"点伤害");
        }
    }
    //A主动技能
    public static boolean A_Skill_A(int count,Player A,Player B){
        if(count%4==0){
            B.setHel(B.getHel()-B_skill_B((int)(A.getAtk()*1.7-B.getDef())));
            //System.out.println(A.getName()+"发动技能,对"+B.getName()+"造成了"+B_skill_B((int)(A.getAtk()*1.7-B.getDef()))+"点伤害，并封印对方行动");
            return true;
        }else{
            return false;
        }

    }
    //A被动技能
    public static void A_Skill_B(Player A,Player B){
        if(randomInt()<=30){
            //System.out.println(A.getName()+"触发技能,沉默"+B.getName());
            A.setSkillModel(1);
        }
    }


    //B普通攻击
    public static void B_DefaultAtk(Player A,Player B){
        if(B.getAtk()>A.getDef()){
            A.setHel(A.getHel()-(B.getAtk()-A.getDef()));
            //System.out.println(B.getName()+"发动了普通攻击,对"+A.getName()+"造成了"+(B.getAtk()-A.getDef())+"点伤害");
            if(B.getSkillModelcount()==1&&A.getSkillModel()!=1){
                int temAtk=(int)Math.round(Math.random()*22+10);
                A.setHel(A.getHel()-temAtk);
                //System.out.println(B.getName()+"对"+A.getName()+"造成"+temAtk+"点元素伤害");
                B.setSkillModelcount(0);
            }
        }
    }
    //B主动技能
    public static boolean B_Skill_A(int count,Player A,Player B){
        if(count%2==0){
            B.setSkillModel(1);
            B.setSkillModelcount(1);
            //System.out.println("华发动技能进入蓄力状态,防御力提升3点");
            return true;
        }else{
            return false;
        }
    }
    public static int B_skill_B(int hurtNum){
        int num=(int)(Math.round(hurtNum)*0.8);
        return num;
    }
}
