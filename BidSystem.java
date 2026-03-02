//package BidSystempackage;
import java.util.*;
import java.io.*;
public class BidSystem
{
    public static void main(String[] args) throws IOException,ClassNotFoundException
    {
        boolean run=true;
            System.out.println("+--------------------------------------------------------------+");
            System.out.println("|        --Welcome To the Bidding System Software--            |");
        while(run){
            System.out.println("+--------------------------------------------------------------+");
            System.out.println("|                         Hello User !                         |");
            System.out.println("|--------------------------------------------------------------|");
            System.out.println("|   choice                               Option                |");
            System.out.println("|   1                            You are here as a Admin       |");
            System.out.println("|   2                            You are here as a Auctioner   |");
            System.out.println("|   3                            You are here as a Seller      |");
            System.out.println("|   4                            You are here as a Bidder      |");
            System.out.println("|   5                            To Close Bidding System       |");
            System.out.println("+--------------------------------------------------------------+");
            BufferedReader br=new BufferedReader(new InputStreamReader (System.in));
            int choice;
            System.out.print(" Enter your Choice...  ");
            choice=Integer.parseInt(br.readLine());
            System.out.println("Coice is "+choice);
            switch (choice)
            {
                case 1:
                    System.out.println("CASE 1");
                    AdminAuth obj=new AdminAuth();
                    obj.main1();
                    break;
                case 2:
                    AuctionerAuth obj1=new AuctionerAuth();
                    obj1.main1();
                    break;
                case 3:
                    SellerAuth obj2=new SellerAuth();
                    obj2.main1();
                    break;
                case 4:
                    BidderAuth obj3=new BidderAuth();
                    obj3.main1();
                    break;
                case 5:
                    run=false;
                    break;
                default:
                    System.out.println("|------------You have enterd wrong choice----------------------|");
            }
        }
        System.out.println("|--------------------------------------------------------------|");
        System.out.println("|  **********Thank You for Using Bidding Software*********     |");
        System.out.println("+--------------------------------------------------------------+");
    }
}