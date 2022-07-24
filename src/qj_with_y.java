/**
 * @author hytabc
 * @date 2022/7/21
 */
public class qj_with_y {
//    Player A = new Player("yd",100,16,12,16);
//    Player B = new Player("qj",100,23,9,26);

    public static void main(String[] args) {
        int A_win=0;
        int B_win=0;
        int fightNum=100000;
        for (int i=0;i<fightNum;i++){
            Player A = new Player("樱",100,24,10,27);
            Player B = new Player("千劫",100,23,9,26);
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
            //System.out.println("回合"+count+":");
            if(A_Skill_B(A,B)){
                //A攻击
                if(A_Skill_A(count,A,B)){

                }else{
                    A_DefaultAtk(A,B);
                }
                //System.out.println(B.getName()+"血量为:"+B.getHel());
                if(B.getHel()<=0){
                    A_win++;
                    break;
                }
                //无敌闪避判定
                int temHel=A.getHel();
                A.setHel(10000);
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
                //System.out.println(A.getName()+"血量为:"+temHel);
                if(A.getHel()<=0){
                    B_win++;
                    break;
                }
                //A回复为正常血量
                A.setHel(temHel);
            }else{
                //A攻击
                if(A_Skill_A(count,A,B)){

                }else{
                    A_DefaultAtk(A,B);
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
            }
        }

        //判断胜负
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
            //System.out.print(A.getName()+"发动了技能，");
            int temNum=(int)Math.round(Math.random()*4+1);
            A.setHel(A.getHel()+temNum);
            //System.out.print("恢复了"+temNum+"点血量,");
            if(A.getHel()>100)A.setHel(100);
            //System.out.print("现在的血量为："+A.getHel()+",");
            B.setHel(B.getHel()-(int)Math.round(A.getAtk()*1.3-B.getDef()));
            //System.out.println("并对"+B.getName()+"造成了"+((int)Math.round(A.getAtk()*1.3-B.getDef()))+"点伤害");
            return true;
        }else{
            return false;
        }
    }
    //A被动技能
    public static boolean A_Skill_B(Player A,Player B){
        if(randomInt()<15){
            //System.out.println(A.getName()+"触发技能，本回合不受伤害");
            return true;
        }else{
            return false;
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
