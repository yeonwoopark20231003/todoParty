package com.example.todoparty.todo;

import com.example.todoparty.user.User;
import com.example.todoparty.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TodoListResponseDTO {
    private UserDTO user;
    private List<TodoResponseDTO> todoList;

    public TodoListResponseDTO(UserDTO user, List<TodoResponseDTO> todoList) {
        this.user = user;
        this.todoList = todoList;
    }
}
