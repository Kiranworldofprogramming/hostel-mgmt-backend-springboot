package com.nt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Student;

public interface IStudentRepo2 extends JpaRepository<Student, Long> {

	public List<Student> findByRegdNo(long regdNo);
	
	public List<Student> findByRoomNo(String roomNo);
	
	Optional<Student> findByid(long regdNo);
}
