package com.example.todoparty.todo;

import com.example.todoparty.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createDate;

    @Column
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(TodoRequestDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.createDate = LocalDateTime.now();
        this.isCompleted = false;
    }

    //연관관계 메서드
    public void setUser(User user){
        this.user = user;
    }

    //서비스 메서드
    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void complete(){
        this.isCompleted = true;
    }

}
