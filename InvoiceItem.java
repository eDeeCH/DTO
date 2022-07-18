package XML;

import java.math.BigDecimal;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceItem", propOrder = {
        "Name",
        "Prise",
        "Amount",
        "Sum"
})
public class InvoiceItem {
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    String Name;
    @XmlElement(required = true)
    int Prise;
    @XmlElement(required = true)
    int Amount;
    @XmlElement(required = true)
    BigDecimal Sum = BigDecimal.valueOf(0);
    InvoiceItem(){}
    InvoiceItem(String name, int prise, int amount ){
        this.Name = name;
        this.Prise = prise;
        this.Amount = amount;
        this.Sum = this.Sum.add(BigDecimal.valueOf(prise * amount));
    }
}

