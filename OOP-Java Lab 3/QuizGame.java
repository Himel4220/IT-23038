import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) {
        
        while(true){
            int score=0;
            int totalScore=25;
            Scanner sc = new Scanner(System.in);
       
       System.out.print("Enter your name :");
       String playerName = sc.nextLine();  
       System.out.print("\tHi "+playerName+"\n");
       System.out.print("----Welcome to the Game----\n");
       
       System.out.println("Answer the following questions:\n");
       System.out.println("Q1:50+100 =?\n\na)150\nb)100\nc)200");

      
       System.out.println("Choose your answer:");
       String ans1=sc.nextLine();
       if(ans1.equals("a")){
        System.out.println("Correct\n");
        score+=5;
       }
       else {
        System.out.println("Incorrect answer\n");
        score--;
       }
       System.out.println("Q2:What is the capital of Bangladesh?\n\na)Tangail\nb)Dhaka\nc)Chittagong");
       
       System.out.println("Choose your answer:");
       String ans2=sc.nextLine();

       if(ans2.equals("b")){
        System.out.println("Correct\n");
        score+=5;
       }
       else {
        System.out.println("Incorrect answer\n");
        score--;
       }

       System.out.println("Q3:Which day is the independence day of Bangladesh?\n\na)22 March\nb)24 March\nc)26 March");
       
       System.out.println("Choose your answer:");
       String ans3=sc.nextLine();

       if(ans3.equals("c")){
        System.out.println("Correct\n");
        score+=5;
       }
       else {
        System.out.println("Incorrect answer\n");
        score--;
       }
       System.out.println("Q4:999-90 =?\n\na)900\nb)901\nc)909");
      
       System.out.println("Choose your answer:");
       String ans4=sc.nextLine();

       if(ans4.equals("c")){
        System.out.println("Correct\n");
        score+=5;
       }
       else {
        System.out.println("Incorrect answer\n");
        score--;
       }
       System.out.println("Q5:25*25 =?\n\na)625\nb)525\nc)225");
    
       System.out.println("Choose your answer:");

       String ans5=sc.nextLine();

       if(ans5.equals("a")){
        System.out.println("Correct\n");
        score+=5;
       }
       else {
        System.out.println("Incorrect answer\n");
        score--;
       }
       
       System.out.println("Do you want to play again if yes then press 1 otherwise press 0");
       int loop = sc.nextInt();
       if(loop==0){
        System.out.println("------Session details-------\n");
        System.out.println("Playername : "+playerName+"\nFinal score : "+score+" out of "+totalScore);
        
        score=0; 
        break;
       }
        }
       
    }
}

