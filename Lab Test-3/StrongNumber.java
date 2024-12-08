import java.util.Scanner;


public class StrongNumber {
    public static int fact(int x){
        int res = 1;
        for(int i=1;i<=x;i++){
            res=res*i;
        }
        return res;
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a range:");
        int range = sc.nextInt();
        for(int i=1;i<=range;i++){
            int sum=0;
            int number =i;
            int n = number;
        while(number>0){
            int rem = number%10;
            int fac = fact(rem);
            sum = sum+fac;
            number = number/10;
        }
        if(sum==n){
            System.out.println("The number is "+n);
            System.out.println("The number is Strong");
        
        }

        }

          }    
}
