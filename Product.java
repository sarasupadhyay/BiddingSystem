package bidding;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Product implements Serializable
{
    String productDescription;
    String productName;
    String productId;
    int startingBid;
    int currentBid;
        Product( String productId,String productName,String productDescription,int startingBid)
        {
             this.productId=productId;
             this.productName=productName;
             this.productDescription=productDescription;
             this.startingBid=startingBid;
             this.currentBid=0;
        }
        public String getProductName()
        {
            return productName;
        }
        public String getProductId()
        {
            return productId;
        }
        public String getProductDescription()
        {
            return productDescription;
        }
        public int getStartingBid()
        {
            return startingBid;
        }
                public int getCurrentBid()
        {
            return currentBid;
        }
        public void setCurrentBid(int amount)
        {
            this.currentBid=amount;
        }
    //@override
    public String toString()
    {
        return ("product Id : "+ productId+",  productName : "+productName+", productDescription: "+productDescription+", startingBid:"+startingBid+", currentBid: "+currentBid);
    }
}
