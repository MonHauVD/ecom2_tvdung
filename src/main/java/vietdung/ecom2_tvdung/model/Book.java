package vietdung.ecom2_tvdung.model;

//import jakarta.persistence.Id;
//import jakarta.persistence.*;
import javax.persistence.*;
@Entity
//@DiscriminatorValue("Book")
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String isbn;
    private Integer numberPage;
    
    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public Book()
    {
    }

    public Book(String author, String isbn, Integer numberPage)
    {
        this.author = author;
        this.isbn = isbn;
        this.numberPage = numberPage;
    }

    public Book(String author, String isbn, Integer numberPage, Item item)
    {
        this.author = author;
        this.isbn = isbn;
        this.numberPage = numberPage;
        this.item = item;
    }

    
    public Book(Long id, String author, String isbn, Integer numberPage, Item item)
    {
        this.id = id;
        this.author = author;
        this.isbn = isbn;
        this.numberPage = numberPage;
        this.item = item;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    @Override
    public String toString()
    {
        return "Book{" + "id=" + id + ", author=" + author + ", isbn=" + isbn + ", numberPage=" + numberPage + ", item=" + item + '}';
    }


    
    
    
}
