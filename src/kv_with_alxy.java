/**
 * @author hytabc
 * @date 2022/7/21
 */
public class kv_with_alxy {

    public static void main(String[] args) {
        int A_win=0;
        int B_win=0;
        int fightNum=100000;
        for (int i=0;i<fightNum;i++){
            Player A = new Player("凯文",100,20,11,21);
            Player B = new Player("爱莉希雅",100,21,8,20);
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
        
        int A_win=0;
        int B_win=0;
        while(A.getHel()>0&&B.getHel()>0){
            count++;
            //System.out.println("回合"+count+":");
            //B技能触发判定
            if(B.getSkillModel()==1){
                //System.out.print(B.getName()+"技能触发，对手攻击力下降6点,");
                A.setAtk(A.getAtk()-6);
                //System.out.println("此时"+A.getName()+"攻击力为"+A.getAtk());
            }
            //A攻击
            if(A_Skill_A(count,A,B)){}
            else{
                A_DefaultAtk(A,B);
            }
            A_Skill_B(A,B);
            //B技能判定清空
            if(B.getSkillModel()==1){
                B.setSkillModel(0);
                A.setAtk(A.getAtk()+6);
                //System.out.println(B.getName()+"技能判定恢复,"+A.getName()+"现在攻击力为"+A.getAtk());
            }
            //System.out.println(B.getName()+"血量为"+B.getHel());
            //判定B血量
            if(B.getHel()<=0){
                A_win++;
                break;
            }
            //B攻击
            if(B_Skill_A(count,A,B)){}
            else{
                B_DefaultAtk(A,B);
                B_Skill_B(A,B);
            }
            //System.out.println(A.getName()+"血量为"+A.getHel());
            //判定A血量
            if(A.getHel()<=0){
                B_win++;
                break;
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
    //A普通攻击
    public static void A_DefaultAtk(Player A,Player B){
        if(A.getAtk()>B.getDef()){
            B.setHel(B.getHel()-(A.getAtk()-B.getDef()));
            //System.out.println(A.getName()+"发动了普通攻击,对"+B.getName()+"造成了"+(A.getAtk()-B.getDef())+"点伤害");
        }
    }
    //A主动技能
    public static boolean A_Skill_A(int count,Player A,Player B){
        if(count%3==0){
            //System.out.print(A.getName()+"触发技能，");
            A.setAtk(A.getAtk()+5);
            //System.out.print("此时攻击力为"+A.getAtk());
            B.setHel(B.getHel()-25);
            //System.out.println(","+B.getName()+"剩余血量为"+B.getHel());
            return true;
        }else{
            return false;
        }
    }
    //A被动技能
    public static boolean A_Skill_B(Player A,Player B){
        if(randomInt()<=30&&B.getHel()<30){
            //System.out.println(A.getName()+"触发技能成功，造成秒杀");
            B.setHel(-10000);
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
    //B主动技能
    public static boolean B_Skill_A(int count,Player A,Player B){
        if(count%2==0){
            //System.out.print(B.getName()+"触发技能,");
            int temNumAtk=(int)Math.round(Math.random()*24+25)-B.getDef();
            A.setHel(A.getHel()-temNumAtk);
            //System.out.println("造成了"+temNumAtk+"点伤害,并使下回合对手攻击力下降6点");
            B.setSkillModel(1);
            return true;
        }else{
            return false;
        }
    }
    //B被动技能
    public static void B_Skill_B(Player A,Player B){
        if(randomInt()<=35){
            //System.out.println(B.getName()+"触发被动技能，追加一次攻击");
            A.setHel(A.getHel()-11);
        }
    }
}
