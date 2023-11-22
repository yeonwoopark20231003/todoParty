package com.example.todoparty.todo;

import com.example.todoparty.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoResponseDTO createPost(TodoRequestDTO dto, User user){
        Todo todo = new Todo(dto);
        todo.setUser(user);
        todoRepository.save(todo);
        return new TodoResponseDTO(todo);
    }
}
