package com.example.todoparty.todo;

import com.example.todoparty.CommonResponseDto;
import com.example.todoparty.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/todos")
@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDTO> postTodo(@RequestBody TodoRequestDTO todoRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        TodoResponseDTO responseDTO = todoService.createPost(todoRequestDto, userDetails.getUser());

        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<CommonResponseDto> getTodo(@PathVariable Long todoId){
      try {
           TodoResponseDTO responseDTO = todoService.getTodo(todoId);
          return ResponseEntity.ok().body(responseDTO);
       }catch (IllegalArgumentException e){
           return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
       }

    }

    @GetMapping
    public ResponseEntity<Void> getTodoList(){
        return ResponseEntity.ok().build();
    }
}
