package dbService.dataSets;

import javax.persistence.*;

@Entity
@Table (name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;


    public User(){

    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
