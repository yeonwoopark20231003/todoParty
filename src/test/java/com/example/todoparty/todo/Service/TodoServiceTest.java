package com.example.todoparty.todo.Service;

import com.example.todoparty.todo.TodoRepository;
import com.example.todoparty.todo.TodoRequestDTO;
import com.example.todoparty.todo.TodoResponseDTO;
import com.example.todoparty.todo.TodoService;
import com.example.todoparty.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) //@Mock 사용 위해 설정
class TodoServiceTest {

    @Mock
    TodoRepository todoRepository;

    @Test
    @DisplayName("정상 Todo 생성 test")
    void test1(){
        //given
        //todo request DTO & user 정보
        String title = "정상 Todo 생성 테스트";
        String content = "정상 Todo 생성 테스트 내용";
        String username = "여누테스트";
        String password = "1234";

        TodoRequestDTO requestDTO = new TodoRequestDTO();
        requestDTO.setTitle(title);
        requestDTO.setContent(content);

        User user = new User(username, password);

        TodoService todoService = new TodoService(todoRepository);

        //when
        TodoResponseDTO result = todoService.createTodo(requestDTO, user);

        //then
        assertEquals(title, result.getTitle());
        assertEquals(content, result.getContent());
        assertEquals(username, result.getUser().getUsername());
    }



}