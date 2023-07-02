package web.jpaRepo;

import org.springframework.data.repository.CrudRepository;

import web.model.Student;

public interface JpaStudentRepository extends CrudRepository<Student, String> {
	
}
