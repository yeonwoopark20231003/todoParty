package com.example.todoparty.todo;

import com.example.todoparty.user.User;
import com.example.todoparty.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public TodoResponseDTO getTodo(Long todoId) {
        Todo todo= todoRepository.findById(todoId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 할일 ID 입니다."));
        return new TodoResponseDTO(todo);
    }

    public Map<UserDTO, List<TodoResponseDTO>> getUserTodoMap() {
        Map<UserDTO, List<TodoResponseDTO>> userTodoMap = new HashMap<>();

        List<Todo> todoList=  todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));// 작성일 기준 내림차순

        todoList.forEach(todo-> {
            var userDTO = new UserDTO(todo.getUser());
            var todoDto = new TodoResponseDTO(todo);
            if (userTodoMap.containsKey(userDTO)){
                //유저 할일 목룍에 항목 추가
                userTodoMap.get(userDTO).add(todoDto);
            }else {
                //유저 할일 목룍을 새로 추가
                userTodoMap.put(userDTO,new ArrayList<>(List.of(todoDto)));
            }

        });

        return userTodoMap;
    }
}
