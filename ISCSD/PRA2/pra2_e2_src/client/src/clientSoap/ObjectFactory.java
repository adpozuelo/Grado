
package clientSoap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the clientSoap package. 
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

    private final static QName _ListAllCategoriesResponse_QNAME = new QName("http://ejb/", "listAllCategoriesResponse");
    private final static QName _Category_QNAME = new QName("http://ejb/", "category");
    private final static QName _ListAllCategories_QNAME = new QName("http://ejb/", "listAllCategories");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: clientSoap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CategoryTO }
     * 
     */
    public CategoryTO createCategoryTO() {
        return new CategoryTO();
    }

    /**
     * Create an instance of {@link ListAllCategories }
     * 
     */
    public ListAllCategories createListAllCategories() {
        return new ListAllCategories();
    }

    /**
     * Create an instance of {@link ListAllCategoriesResponse }
     * 
     */
    public ListAllCategoriesResponse createListAllCategoriesResponse() {
        return new ListAllCategoriesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAllCategoriesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb/", name = "listAllCategoriesResponse")
    public JAXBElement<ListAllCategoriesResponse> createListAllCategoriesResponse(ListAllCategoriesResponse value) {
        return new JAXBElement<ListAllCategoriesResponse>(_ListAllCategoriesResponse_QNAME, ListAllCategoriesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoryTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb/", name = "category")
    public JAXBElement<CategoryTO> createCategory(CategoryTO value) {
        return new JAXBElement<CategoryTO>(_Category_QNAME, CategoryTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAllCategories }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb/", name = "listAllCategories")
    public JAXBElement<ListAllCategories> createListAllCategories(ListAllCategories value) {
        return new JAXBElement<ListAllCategories>(_ListAllCategories_QNAME, ListAllCategories.class, null, value);
    }

}
