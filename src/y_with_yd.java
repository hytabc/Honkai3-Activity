/**
 * @author hytabc
 * @date 2022/7/21
 */
public class y_with_yd {

    public static void main(String[] args) {
        int A_win=0;
        int B_win=0;
        int fightNum=100000;
        for (int i=0;i<fightNum;i++){
            Player A = new Player("伊甸",100,16,12,16);
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
            if(B_Skill_B(A,B)){
                //A触发先手攻击
                if(A_FirstAttack==1){
                    //无敌闪避判定
                    int temHel=B.getHel();
                    B.setHel(10000);
                    //System.out.println(A.getName()+"触发先手攻击");
                    A_FirstAttack-=1;
                    //A攻击
                    if(A_Skill_A(count,A,B)){
                        A_FirstAttack=1;
                        //System.out.println(A.getName()+"发动技能成功,下回合将变为先手攻击。");
                    }
                    else{
                        A_DefaultAtk(A,B);
                        A_Skill_B(A,B);
                    }
                    //System.out.println(B.getName()+"血量为:"+temHel);
                    if(B.getHel()<=0){
                        A_win++;
                        break;
                    }
                    //B回复为正常血量
                    B.setHel(temHel);
                    //B攻击
                    if(B_Skill_A(count,A,B)){

                    }else{
                        B_DefaultAtk(A,B);
                    }
                    //System.out.println(A.getName()+"血量为:"+A.getHel());
                    if(A.getHel()<=0){
                        B_win++;
                        break;
                    }
                }else{
                    //B攻击
                    if(B_Skill_A(count,A,B)){

                    }else{
                        B_DefaultAtk(A,B);
                    }
                    //System.out.println(A.getName()+"血量为:"+A.getHel());
                    if(A.getHel()<=0){
                        B_win++;
                        break;
                    }
                    //无敌闪避判定
                    int temHel=B.getHel();
                    B.setHel(10000);
                    //A攻击
                    if(A_Skill_A(count,A,B)){
                        A_FirstAttack=1;
                    }
                    else{
                        A_DefaultAtk(A,B);
                        A_Skill_B(A,B);
                    }
                    //System.out.println(B.getName()+"血量为:"+temHel);
                    if(B.getHel()<=0){
                        A_win++;
                        break;
                    }
                    //B回复为正常血量
                    B.setHel(temHel);
                }

            }else{
                //A触发先手攻击
                if(A_FirstAttack==1){
                    //System.out.println(A.getName()+"触发先手攻击");
                    A_FirstAttack-=1;
                    //A攻击
                    if(A_Skill_A(count,A,B)){
                        A_FirstAttack=1;
                        //System.out.println(A.getName()+"发动技能成功,下回合将变为先手攻击。");
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
                    if(B_Skill_A(count,A,B)){

                    }else{
                        B_DefaultAtk(A,B);
                    }
                    //System.out.println(A.getName()+"血量为:"+A.getHel());
                    if(A.getHel()<=0){
                        B_win++;
                        break;
                    }
                }else{
                    //B攻击
                    if(B_Skill_A(count,A,B)){

                    }else{
                        B_DefaultAtk(A,B);
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
            return false;
        }
    }
}
