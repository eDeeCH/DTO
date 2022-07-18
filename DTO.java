package XML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DTO {

    public static void main(String[] args) {
        try {
           JAXBContext context = JAXBContext.newInstance(Invoice.class);
           Marshaller m = context.createMarshaller();
           Invoice invoice = new Invoice();
           invoice.AddInvoiceItem("Hibernate", 500, 5);
           invoice.AddInvoiceItem("Spring", 5000, 1);
           m.marshal(invoice, new FileOutputStream("src/Invoice1.xml"));
           System.out.println("XML - файл создан, спустя-то сколько времени");
        }
        catch (JAXBException | FileNotFoundException e){
            System.out.println(e);
        }
    }
}
