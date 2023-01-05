package hellojpa;


import javax.persistence.Entity;

@Entity
public class Book extends item{
    private String author;
    private String isbn;
}
