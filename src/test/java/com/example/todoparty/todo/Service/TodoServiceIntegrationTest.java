package com.example.todoparty.todo.Service;

import com.example.todoparty.todo.*;
import com.example.todoparty.user.User;
import com.example.todoparty.user.UserDTO;
import com.example.todoparty.user.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TodoServiceIntegrationTest {

    @Autowired
    TodoService todoService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TodoRepository todoRepository;


    User user;
    TodoResponseDTO createdTodo = null;

    @Test
    @Order(1)
    @DisplayName("Todo 신규 생성 test")
    void test1() {
        //given
        String title = "정상 Todo 생성 테스트";
        String content = "정상 Todo 생성 테스트 내용";


        TodoRequestDTO requestDTO = new TodoRequestDTO();
        requestDTO.setTitle(title);
        requestDTO.setContent(content);

        user = userRepository.findById(2L).orElse(null);

        //when
        TodoResponseDTO todo = todoService.createTodo(requestDTO, user);

        //then
        assertEquals(title, todo.getTitle());
        assertEquals(content, todo.getContent());
        createdTodo = todo;
    }


    @Test
    @Order(2)
    @DisplayName("생성된 Todo 수정")
    void test2() {
        //given
        Long todoId = this.createdTodo.getId();
        //user = userRepository.findById(2L).orElse(null);
        String title = "Todo 수정 테스트";
        String content = "Todo 수정 테스트: 내용";

        TodoRequestDTO requestDTO = new TodoRequestDTO();
        requestDTO.setTitle(title);
        requestDTO.setContent(content);

        user = userRepository.findById(2L).orElse(null);

        //when
        TodoResponseDTO todo = todoService.updateTodo(todoId, requestDTO, user);

        //then
        assertEquals(title, todo.getTitle());
        assertEquals(content, todo.getContent());
        createdTodo = todo;
    }

    @Test
    @Order(3)
    @DisplayName("유저가 등록한 todo 조회")
    void test3(){
        //given
        //UserDTO userDTO = new UserDTO(user);
        user = userRepository.findById(2L).orElse(null);

        //when
        Map<UserDTO, List<TodoResponseDTO>> userTodoMap = todoService.getUserTodoMap();


        //then
        Long createdTodoId = this.createdTodo.getId();

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



        assertEquals(title, todo.getTitle());
        assertEquals(content, todo.getContent());


    }




}




