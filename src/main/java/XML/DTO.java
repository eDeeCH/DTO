package XML;

import jakarta.xml.bind.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;



public class DTO {

    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Invoice.class);

            Marshaller m = context.createMarshaller();

            Invoice invoice = new Invoice();

            invoice.AddInvoiceItem("Spring", 5000, 5);

            invoice.AddInvoiceItem("Hibernate", 2000, 10);

            m.marshal(invoice, new FileOutputStream("src/main/java/invoice1.xml"));

            System.out.println("XML создан");
        } catch (JAXBException e) {
            System.out.println(e);
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        try {
            JAXBContext context = JAXBContext.newInstance(Invoice.class);
            Unmarshaller m = context.createUnmarshaller();
            FileReader read = new FileReader("src/main/java/invoice1.xml");
            Invoice invoice = (Invoice) m.unmarshal(read);
            System.out.println(invoice);
        }
        catch (JAXBException e) {
            System.out.println(e);
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
    }
}