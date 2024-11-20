package vietdung.ecom2_tvdung.model;

//import jakarta.persistence.Id;
//import jakarta.persistence.*;
import javax.persistence.*;
@Entity
@DiscriminatorValue("Book")
public class Book extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private Integer numberPage;
    private Long idItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNumberPage()
    {
        return numberPage;
    }

    public void setNumberPage(Integer numberPage)
    {
        this.numberPage = numberPage;
    }

   

    public Long getIdItem()
    {
        return idItem;
    }

    public void setIdItem(Long idItem)
    {
        this.idItem = idItem;
    }
    
    
}
