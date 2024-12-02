import java.util.*;

public class Quiz {
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.println("Enter Name: ");
        String name=input.nextLine();
        System.out.println("Enter ID: ");
        String id= input.nextLine();
        double sum=0;
        double finalResult;
        int totalCredit=0;
        for (int i = 1; i <= 3; i++) {
            System.out.println("Course-" + i);

            System.out.println("Provide Credit (Max 3)");
            int credit = input.nextInt();

            System.out.println("Provide CT Marks (Max 30)");
            int ct = input.nextInt();

            System.out.println("Provide Attendance Marks (Max 10)");
            int attendance = input.nextInt();

            System.out.println("Provide Semester Final Mark (Max 60)");
            int finalMark = input.nextInt();

            int total = ct + attendance + finalMark;

            double tempGrade;

            if (total >= 80) tempGrade = 4;
            else if (total >= 75) tempGrade = 3.75;
            else if (total >= 70) tempGrade = 3.5;
            else if (total >= 65) tempGrade = 3.25;
            else if (total >= 60) tempGrade = 3;
            else if (total >= 55) tempGrade = 2.75;
            else if (total >= 50) tempGrade = 2.50;
            else if (total >= 45) tempGrade = 2.25;
            else if (total >= 40) tempGrade = 2;
            else tempGrade = 0;

            totalCredit=totalCredit+credit;
            sum=sum+(tempGrade*credit);
        }
        finalResult=sum/totalCredit;

        System.out.println("Name   : "+name);
        System.out.println("ID     : "+id);
        System.out.printf("CGPA    : %.2f\n",finalResult);
        if (finalResult >= 3.80) System.out.println("Grade: A+");
        else if (finalResult >= 3.75)System.out.println("Grade: A");
        else if (finalResult >= 3.50) System.out.println("Grade: A-");
        else if (finalResult >= 3.25) System.out.println("Grade: B+");
        else if (finalResult >= 3.00) System.out.println("Grade: B");
        else if (finalResult >= 2.75) System.out.println("Grade: B-");
        else if (finalResult >= 2.50) System.out.println("Grade: C+");
        else if (finalResult >= 2.25) System.out.println("Grade: C");
        else if (finalResult >= 2) System.out.println("Grade: D");
        else System.out.println("Grade: F");
    }
}
