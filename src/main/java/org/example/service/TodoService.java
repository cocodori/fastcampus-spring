package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoModel;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 아이템 추가
    public TodoModel add(TodoRequest request) {
        TodoModel todoModel = new TodoModel();
        todoModel.setTitle(request.getTitle());
        todoModel.setOrder(request.getOrder());
        todoModel.setComplete(request.getCompleted());

        TodoModel savedEntity = todoRepository.save(todoModel);

        return savedEntity;
    }

    //아이템 조회
    public TodoModel searchById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // 아이템 전체 조회
    public List<TodoModel> searchAll() {
        return todoRepository.findAll();
    }

    // 아이템 수정
    public TodoModel updateById(Long id, TodoRequest todoRequest) {
        TodoModel todoModel = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (todoRequest.getTitle() != null)
            todoModel.setTitle(todoRequest.getTitle());
        if (todoRequest.getOrder() != null)
            todoModel.setOrder(todoRequest.getOrder());
        if (todoRequest.getOrder() != null)
            todoModel.setComplete(todoRequest.getCompleted());

        return todoRepository.save(todoModel);
    }

    // 아이템 삭제
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }


    // 아이템 전체 삭제
    public void deleteAll() {
        todoRepository.deleteAll();
    }
}
