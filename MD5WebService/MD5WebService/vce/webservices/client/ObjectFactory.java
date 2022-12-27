
package vce.webservices.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the vce.webservices.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _HashStringResponse_QNAME = new QName("http://server.webservices.vce/", "hashStringResponse");
    private final static QName _HashString_QNAME = new QName("http://server.webservices.vce/", "hashString");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: vce.webservices.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HashString }
     * 
     */
    public HashString createHashString() {
        return new HashString();
    }

    /**
     * Create an instance of {@link HashStringResponse }
     * 
     */
    public HashStringResponse createHashStringResponse() {
        return new HashStringResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HashStringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservices.vce/", name = "hashStringResponse")
    public JAXBElement<HashStringResponse> createHashStringResponse(HashStringResponse value) {
        return new JAXBElement<HashStringResponse>(_HashStringResponse_QNAME, HashStringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HashString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservices.vce/", name = "hashString")
    public JAXBElement<HashString> createHashString(HashString value) {
        return new JAXBElement<HashString>(_HashString_QNAME, HashString.class, null, value);
    }

}
