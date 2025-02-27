package com.nt.repository;



import org.springframework.data.repository.CrudRepository;

import com.nt.model.Student;

public interface IStudentRepo extends CrudRepository<Student, Long> {
	
	
}
