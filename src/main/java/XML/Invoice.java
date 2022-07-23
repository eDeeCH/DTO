package XML;

import jakarta.xml.bind.annotation.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Invoice", propOrder = {
        "ID",
        "status",
        "date",
        "InvoiceItems",
        "TotalAmount"
})
public class Invoice {
    @XmlElement(required = true)
    BigDecimal TotalAmount = BigDecimal.valueOf(0);
    @XmlElement(required = true)
    Date date;
    @XmlElement(required = true)
    ArrayList<InvoiceItem> InvoiceItems = new ArrayList<InvoiceItem>();
     enum Status {
        Paid,
        NoPaid,
        Canceled,
        Returned
    }
    @XmlElement(required = true)
    Status status;
    @XmlElement(required = true)
     int ID;
    {
        date = new Date();
        status = Status.NoPaid;
        try(DataInputStream dos = new DataInputStream(new FileInputStream("src/main/java/ID")))
        {
            ID = dos.readInt();
        }

        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/main/java/ID")))
        {
            dos.writeInt(ID + 1);
        }

        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    public Invoice(){}

    public void AddInvoiceItem(String name, int prise, int amount){
        date = new Date();
        InvoiceItem Item =  new InvoiceItem(name, prise, amount);
        InvoiceItems.add(Item);
        TotalAmount = TotalAmount.add(Item.Sum);
    }
    public void changeStatus(Status status){
        this.status = status;
    }
    public String toString(){
        return "ID:\t" + ID +
                "\nstatus:\t" + status +
                "\ndate:\t" + date +
                "\nInvoiceItem:\t" + InvoiceItems +
                "\nTotalAmount:\t" + TotalAmount;
    }
}

