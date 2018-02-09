package myMVC.controller;

/**
 *  三门问题
 */
public class MontyHallProblem {
    // 实验的总次数
    private int N;
    // 实验的门数
    private int DoorNumber;

    private MontyHallProblem(int N,int DoorNumber){
        if(N <= 0 || DoorNumber <= 0){
            throw new IllegalArgumentException("N or DoorNumber must be larger than zero!");
        }
        this.N = N;
        this.DoorNumber = DoorNumber;
    }

    // 传入参数,代表每次换或者不换门
    private void run(boolean changeDoor){
        int wins = 0;
        for (int i = 0; i < N; i++){
            if(play(changeDoor)){
                wins ++;
            }
        }
        System.out.println(changeDoor ? "Change" : "Not Change");
        System.out.println("winning rate: " + (double)wins/N);
    }

    private boolean play(boolean changeDoor){
        // Door  0,1,2 ... DoorNumber
        int prizeDoor = (int)(Math.random() * DoorNumber);
        int playerChoice = (int)(Math.random() * DoorNumber);
        // 如果中奖换门,则返回false,不中奖换门返回true
        if(playerChoice == prizeDoor){
            return changeDoor ? false : true;
        }else {
            return changeDoor ? true : false;
        }
    }

    public static void main(String[] args) {
        int  N = 100000000;
        int DoorNumber = 3;
        MontyHallProblem mhp = new MontyHallProblem(N,DoorNumber);
        mhp.run(true);
        mhp.run(false);
    }
}
