package com.example.todoparty.todo;

import com.example.todoparty.CommonResponseDto;
import com.example.todoparty.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoResponseDTO extends CommonResponseDto {
    private Long id;
    private String title;
    private String content;
    private UserDTO user;
    private LocalDateTime createDate;
    private Boolean isCompleted;

    public TodoResponseDTO(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.isCompleted = getIsCompleted();
        this.user = new UserDTO(todo.getUser()) ;
        this.createDate = todo.getCreateDate();
    }
}
