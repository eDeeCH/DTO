package XML;

import javax.xml.bind.annotation.*;
import java.io.*;
import java.math.BigDecimal;
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
    InvoiceItem[] InvoiceItems = new InvoiceItem[0];
    enum Status {
        Paid,
        NoPaid,
        Canceled,
        Returned
    }
    @XmlElement(required = true)
    Status status;
    @XmlElement(required = true)
    public int ID;

    {
        date = new Date();
        status = Status.NoPaid;

        try(DataInputStream dos = new DataInputStream(new FileInputStream("ID")))
        {
            ID = dos.readInt();
        }

        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("ID")))
        {
            dos.writeInt(ID + 1);
        }

        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public void AddInvoiceItem(String name, int prise, int amount){
        date = new Date();
        InvoiceItems = new InvoiceItem[InvoiceItems.length + 1];
        InvoiceItems[InvoiceItems.length - 1] =  new InvoiceItem(name, prise, amount);
        TotalAmount = TotalAmount.add(InvoiceItems[InvoiceItems.length - 1].Sum);
    }
    @XmlElement
    public void GetChangeStatusPaid(){
        status = Status.Paid;
    }
    @XmlElement
    public void GetChangeStatusCanceled(){
        status = Status.Canceled;
    }
    @XmlElement
    public void GetChangeStatusReturned(){
        status = Status.Returned;
    }
}

