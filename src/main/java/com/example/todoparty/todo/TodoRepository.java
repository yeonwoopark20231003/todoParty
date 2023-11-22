package com.example.todoparty.todo;

import com.example.todoparty.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
