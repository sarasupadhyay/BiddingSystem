package bidding;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
public class Seller implements Serializable
{
    String sellerEmail;
    String sellerName;
    String sellerId;
    String password;
        Seller( String sellerId,String password,String sellerEmail,String sellerName)
        {
             this.sellerEmail=sellerEmail;
             this.sellerName=sellerName;
             this.sellerId=sellerId;
             this.password=password;
        }
        public String getSellerName()
        {
            return sellerName;
        }
        public String getSellerId()
        {
            return sellerId;
        }
        public String getSellerEmail()
        {
            return sellerEmail;
        }
        public String getpassword()
        {
            return password;
        }
    public String toString()
    {
        return ("Seller Id : "+ sellerId+", password is : "+password+", SellerName : "+sellerName+", sellerEmail : "+sellerEmail);
    }
}
class SellerAuth
{
    static final String Seller_File="./bidding/sellers.dat";
    static final String PRODUCT_FILE="./bidding/product.dat";
    public static void main1() throws IOException,ClassNotFoundException
        {
        boolean run=true;
        System.out.println("|--------------------------------------------------------------|");
        System.out.println("|      --Welcome Seller To the Bidding System Software--       |");
        while(run){
        System.out.println("|--------------------------------------------------------------|");
        System.out.println("|--------------------------------------------------------------|");
        System.out.println("|                        Hi Seller !                           |");
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
            System.out.print("| Enter the Seller Name: ");
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
            if(pass1.equals(pass2))
            flag=false;
            else
            System.out.println("| Your both password does not match Enter again");
            }
            String id="seller"+(System.currentTimeMillis()%1000000);
            Seller obj=new Seller(id,pass1,email,name);//creating object of seller
            if(addSellerToFile(obj))
            {
                System.out.println("|   Sign Up Successfully ");
                System.out.println("| Your sellerID is : "+id);
            }
            else
                System.out.println("| SignUp Failed could not save seller info");
    }

    static boolean addSellerToFile(Seller obj) throws IOException,ClassNotFoundException
    {
        List<Seller> sellers=loadSellersFromFile();
        sellers.add(obj);
        if(saveSellersToFile(sellers))
        return true;
        else
        return false;
    }

    static List<Seller> loadSellersFromFile() throws ClassNotFoundException
    {
        File file = new File(Seller_File);
        if (!file.exists())
        {
            return new ArrayList<Seller>(); // return empty if file doesn't exist
        }

        try
        { 
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        return (List<Seller>) ois.readObject();
        }
        catch (IOException e)
        {
             return new ArrayList<Seller>();
        }
    }

    static boolean saveSellersToFile(List<Seller> sellers) throws FileNotFoundException
    {
        try{    
        ObjectOutputStream file=new ObjectOutputStream(new FileOutputStream(Seller_File));
         file.writeObject(sellers);
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
        ObjectOutputStream file=new ObjectOutputStream(new FileOutputStream(Seller_File));
        List<Seller> sellers = new ArrayList<Seller>();
         file.writeObject(sellers);
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
     System.out.print("| Enetr the sellerID : ");
     sid=br.readLine();
     System.out.println();
     System.out.print("| Enter the passwords: ");
     pass=br.readLine();
     System.out.println();
     Seller loginSeller=validateSeller(sid,pass);
     if(loginSeller!=null)
     {
         System.out.println("        --------------Login Successfull ------------");
        System.out.println("|  Welcome    "+loginSeller.getSellerName());
        System.out.println("|  sellerId : "+loginSeller.getSellerId());
        //go to dashboard
        while (true) 
             {
                BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                System.out.println("| choose           option     ");
                System.out.println("| 1                 List a Product");
                System.out.println("| 2                 view Listed Products");
                System.out.println("|3                   Log Out ");
                System.out.print("| Enter your choice: ");
                int opt = Integer.parseInt(br1.readLine());
                switch(opt)
                {
                    case 1:
                         listProduct(loginSeller);
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
     br.close();
    }
    static Seller validateSeller(String sellerid, String pass) throws ClassNotFoundException
    {
        List<Seller> sellers=loadSellersFromFile();
        for(Seller s: sellers)
        {
            if((s.getSellerId().equals(sellerid)) && (s.getpassword().equals(pass)))
            return s;
        }
        return null;
    }
    public static void listProduct(Seller seller) throws IOException, ClassNotFoundException
    {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        System.out.print("| Enter Product Name: ");
        String name = br.readLine();
        System.out.print("| Enter Product Description: ");
        String desc = br.readLine();
        System.out.print("| Enter Starting Bid: ");
        int bid = Integer.parseInt(br.readLine());
        String pid="product"+(System.currentTimeMillis()%1000);
           Product product = new Product(pid, name, desc, bid);

        List<Product> products = loadProductsFromFile();
        products.add(product);
        saveProductsToFile(products);
        System.out.println("| Product listed successfully with ID: " + pid);
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
        System.out.println("\n| ---- Listed Products ----");
        for (Product p : products) {
            System.out.println("| " + p.toString());
        }
    }
}
}
