package io.rc.learning.TaskMgt.repository;

import io.rc.learning.TaskMgt.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository <Task, Long> {

}
