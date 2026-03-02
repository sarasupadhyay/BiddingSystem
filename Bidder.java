package bidding;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Bidder implements Serializable
{
    String BidderEmail;
    String BidderName;
    String BidderId;
    String password;
        Bidder( String BidderId,String password,String BidderEmail,String BidderName)
        {
             this.BidderEmail=BidderEmail;
             this.BidderName=BidderName;
             this.BidderId=BidderId;
             this.password=password;
        }
        public String getBidderName()
        {
            return BidderName;
        }
        public String getBidderId()
        {
            return BidderId;
        }
        public String getBidderEmail()
        {
            return BidderEmail;
        }
        public String getpassword()
        {
            return password;
        }
    //@override
    public String toString()
    {
        return ("Bidder Id : "+ BidderId+", password is : "+password+", BidderName : "+BidderName+", BidderEmail : "+BidderEmail);
    }
}
 class BidderAuth
{
    static final String Bidder_File="./bidding/bidders.dat";
    static final String PRODUCT_FILE="./bidding/product.dat";
    public static void main1() throws IOException,ClassNotFoundException
        {
        boolean run=true;
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("|    --Welcome Bidder To the Bidding System Software--         |");
        while(run){
        System.out.println("|--------------------------------------------------------------|");
        System.out.println("|                        Hi Bidder !                           |");
        System.out.println("|--------------------------------------------------------------|");
        System.out.println("|   choice                               Option                |"); 
        System.out.println("|   1                            Already have account Login in |"); 
        System.out.println("|   2                            Not having account Sign Up    |");
        System.out.println("|   3                            TO Exit                       |"); 
        System.out.println("|  Enter your choice--------                                   |");
        //System.out.println("+--------------------------------------------------------------+");
        int choice;
        System.out.print("  ");
        BufferedReader br=new BufferedReader(new InputStreamReader (System.in));
        String input=br.readLine();
        choice=Integer.parseInt(input);
        System.out.println();
            switch (choice)
            {
                case 1:
                login();
                break;
                case 2:
                signUp();
                break;
                case 3:
                    run=false;
                    break;
                case 4:
                    eraseFile();
                    break;
                default:
                System.out.println("|  You have entered wrong choice");
            }
        }
            System.out.println("|--------------------------------------------------------------|");  
        }
        public static void signUp() throws IOException,ClassNotFoundException
        {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String email,name;
            System.out.print("| Enter the Bidder Name: ");
            name=br.readLine();
            System.out.println();
            System.out.print("| Enter the Email : ");
            email=br.readLine();
            System.out.println();
            String pass1="",pass2="";
            boolean flag=true;
            while(flag)
            {
            System.out.print("| Enter your password in Sting format : ");
            pass1=br.readLine();
            System.out.println();
            System.out.print("| Again Enter the same password as above to confirm : ");
            pass2=br.readLine();
            System.out.println();
            if(pass1.equals(pass2)) {
                flag = false;
            }
            else {
                System.out.println("| Your both password does not match Enter again");
            }
            }
            String id="Bidder"+(System.currentTimeMillis()%1000000);
            Bidder obj=new Bidder(id,pass1,email,name);//creating object of Bidder
            if(addBidderToFile(obj))
            {
                System.out.println("|   Sign Up Successfully ");
                System.out.println("| Your BidderID is : "+id);
            }
            else
                System.out.println("| SignUp Failed could not save Bidder info");
    }

    static boolean addBidderToFile(Bidder obj) throws IOException,ClassNotFoundException
    {
        List<Bidder> bidders=loadBiddersFromFile();
        bidders.add(obj);
        if(saveBiddersToFile(bidders))
        return true;
        else
        return false;
    }

    static List<Bidder> loadBiddersFromFile() throws ClassNotFoundException
    {
        File file = new File(Bidder_File);
        if (!file.exists())
        {
            return new ArrayList<Bidder>(); // return empty if file doesn't exist
        }

        try
        { 
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        return (List<Bidder>) ois.readObject();
        }
        catch (IOException e)
        {
             return new ArrayList<Bidder>();
        }
    }

    static boolean saveBiddersToFile(List<Bidder> bidders) throws FileNotFoundException
    {
        try{    
        ObjectOutputStream file=new ObjectOutputStream(new FileOutputStream(Bidder_File));
         file.writeObject(bidders);
        return true;
        }
        catch(IOException e)
        {
            return false;
        }
    }
        static boolean eraseFile() throws FileNotFoundException
    {
        try{    
        ObjectOutputStream file=new ObjectOutputStream(new FileOutputStream(Bidder_File));
        List<Bidder> Bidders = new ArrayList<Bidder>();
         file.writeObject(Bidders);
        return true;
        }
        catch(IOException e)
        {
            return false;
        }
    }
    public static void login() throws IOException, ClassNotFoundException
    {
    System.out.println(" ---------------Welcome To Login------------------");
     String sid ,pass;
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     System.out.print("| Enetr the BidderID : ");
     sid=br.readLine();
     System.out.println();
     System.out.print("| Enter the passwords: ");
     pass=br.readLine();
     System.out.println();
     Bidder loginBidder=validateBidder(sid,pass);
     if(loginBidder!=null)
     {
         System.out.println("        --------------Login Successfull ------------");
        System.out.println("|  Welcome    "+loginBidder.getBidderName());
        System.out.println("|  BidderId : "+loginBidder.getBidderId());
        //go to dashboard
        while (true) 
             {
                 BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                System.out.println("| choose           option     ");
                System.out.println("| 1                 Bid Product");
                System.out.println("| 2                 view Listed Products");
                System.out.println("|3                   Log Out ");
                System.out.print("| Enter your choice: ");
                int opt = Integer.parseInt(br1.readLine());
                switch(opt)
                {
                    case 1:
                         placeBid(loginBidder);
                        break;
                    case 2:
                        displayAllProduct();
                        break;
                    case 3:
                         System.out.println("| Logged out.");
                        return;
                    default:
                        System.out.println("| Invalid choice. ");

                }
            }
        
     }
     else
     {
       System.out.println("| You have entered wrong Id or Password");
     }
    // br.close();
    }

    static Bidder validateBidder(String Bidderid, String pass) throws ClassNotFoundException
    {
        List<Bidder> Bidders=loadBiddersFromFile();
        for(Bidder s: Bidders)
        {
            if((s.getBidderId().equals(Bidderid)) && (s.getpassword().equals(pass)))
            return s;
        }
        return null;
    }

   public static void placeBid(Bidder bidder) throws IOException, ClassNotFoundException 
   {
        List<Product> products = loadProductsFromFile();
        if (products.isEmpty())
     {
        System.out.println("| No products available to bid.");
        return;
    }
    System.out.println("| ---- Products Available for Bidding ----");
     displayAllProduct();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("| Enter the productId to bid on: ");
    String prid=br.readLine();
    boolean matched=false;
        for(Product pr: products)
        {
            if(pr.getProductId().equals(prid))
            {
                matched=true;
                System.out.println("| Starting  bid is : " + pr.getStartingBid());
                System.out.println("| Current highest bid: " + pr.getCurrentBid());
                while(true)
                {
                System.out.println("| Enter your bid (must be higher than current bid): ");
                int newBid = Integer.parseInt(br.readLine());
                if (newBid <= pr.getCurrentBid())
                System.out.println("| Bid must be higher than the current bid!");
                else
                {
                pr.setCurrentBid(newBid);
                saveProductsToFile(products);
                System.out.println("| Your bid has been placed successfully!");
                 break;
                }
              }
            }
        }
        if(matched==false)
         {
            System.out.println("| Invalid product selection.");
        }
   }
    static List<Product> loadProductsFromFile() throws ClassNotFoundException 
    {
        File file = new File(PRODUCT_FILE);
        if (!file.exists())
         return new ArrayList<>();
        try
        { 
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            return (List<Product>) ois.readObject();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    // Save products to file
    static void saveProductsToFile(List<Product> products) throws IOException
     {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PRODUCT_FILE))) 
        {
            oos.writeObject(products);
        }
    }
    // Display all listed products
public static void displayAllProduct() throws ClassNotFoundException {
    List<Product> products = loadProductsFromFile();
    if (products.isEmpty()) {
        System.out.println("| No products have been listed yet.");
    } else {
        System.out.println("| ---- Listed Products ----");
        for (Product p : products) {
            System.out.println("| " + p.toString());
        }
    }
}
}
