/**
 * @author hytabc
 * @date 2022/7/21
 */
public class y_with_abny {

    public static void main(String[] args) {
        int A_win=0;
        int B_win=0;
        int fightNum=100000;
        for (int i=0;i<fightNum;i++){
            Player A = new Player("阿波尼亚",100,21,10,30);
            Player B = new Player("樱",100,24,10,27);
            if(fight(A,B)){
                A_win++;
            }else{
                B_win++;
            }
        }
        System.out.println("总战斗次数"+fightNum);
        //System.out.println("A胜场为："+A_win);
        //System.out.println("B胜场为："+B_win);
        double A_w=((double)A_win/(double)fightNum)*100;//A胜率
        double B_w=((double)B_win/(double)fightNum)*100;//B胜率
        System.out.println("阿波尼亚胜率为"+A_w+"%");
        System.out.println("樱胜率为"+B_w+"%");
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
            if(B_Skill_B(A,B)){
                //无敌闪避判定
                int temHel=B.getHel();
                B.setHel(10000);
                //A攻击
                if(A_Skill_A(count,A,B)){
                    B_rest=1;
                }else{
                    A_DefaultAtk(A,B);
                }
                A_Skill_B(A,B);
                //System.out.println(B.getName()+"血量为:"+temHel);
                if(B.getHel()<=0){
                    A_win++;
                    break;
                }
                //B回复为正常血量
                B.setHel(temHel);
                //B回合判定
                if(B_rest==1){
                    //System.out.println(B.getName()+"本回合被封印");
                    B_rest=0;
                }else{
                    //B攻击
                    if(A.getSkillModel()==1){
                        //System.out.println(B.getName()+"被沉默，无法使用技能");
                        B_DefaultAtk(A,B);
                        A.setSkillModel(0);
                    }else if(B_Skill_A(count,A,B)){

                    }else{
                        B_DefaultAtk(A,B);
                    }
                    //System.out.println(A.getName()+"血量为:"+A.getHel());
                    if(A.getHel()<=0){
                        B_win++;
                        break;
                    }
                }
            }else{
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
                    //B攻击
                    if(A.getSkillModel()==1){
                        //System.out.println(B.getName()+"被沉默，无法使用技能");
                        B_DefaultAtk(A,B);
                        A.setSkillModel(0);
                    }else if(B_Skill_A(count,A,B)){

                    }else{
                        B_DefaultAtk(A,B);
                    }
                    //System.out.println(A.getName()+"血量为:"+A.getHel());
                    if(A.getHel()<=0){
                        B_win++;
                        break;
                    }
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
            B.setHel(B.getHel()-(A.getAtk()-B.getDef()));
            //System.out.println(A.getName()+"发动了普通攻击,对"+B.getName()+"造成了"+(A.getAtk()-B.getDef())+"点伤害");
        }
    }
    //A主动技能
    public static boolean A_Skill_A(int count,Player A,Player B){
        if(count%4==0){
            B.setHel(B.getHel()-(int)(A.getAtk()*1.7));
            //System.out.println(A.getName()+"发动技能,对"+B.getName()+"造成了"+(int)(A.getAtk()*1.7)+"点伤害，并封印对方行动");
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
        }
    }
    //A主动技能
    public static boolean B_Skill_A(int count,Player A,Player B){
        if(count%2==0){
            //System.out.print(B.getName()+"发动了技能，");
            int temNum=(int)Math.round(Math.random()*4+1);
            B.setHel(B.getHel()+temNum);
            //System.out.print("恢复了"+temNum+"点血量,");
            if(B.getHel()>100)B.setHel(100);
            //System.out.print("现在的血量为："+B.getHel()+",");
            A.setHel(A.getHel()-(int)Math.round(B.getAtk()*1.3-A.getDef()));
            //System.out.println("并对"+A.getName()+"造成了"+((int)Math.round(B.getAtk()*1.3-A.getDef()))+"点伤害");
            return true;
        }else{
            return false;
        }
    }
    //A被动技能
    public static boolean B_Skill_B(Player A,Player B){
        if(randomInt()<15){
            //System.out.println(B.getName()+"触发技能，本回合不受伤害");
            return true;
        }else{
            //System.out.println(B.getName()+"触发闪避失败");
            return false;
        }
    }
}
