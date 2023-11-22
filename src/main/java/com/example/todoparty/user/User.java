package com.example.todoparty.user;

import com.example.todoparty.todo.Todo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table (name="users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private  String username;

    @Column (nullable = false)
    private String password;

//    @OneToMany (mappedBy = "user", cascade = CascadeType.REMOVE)
//    private List<Todo> todoList;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }


}
