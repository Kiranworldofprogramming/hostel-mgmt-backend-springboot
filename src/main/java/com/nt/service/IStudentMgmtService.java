package com.nt.service;

import java.util.List;

import com.nt.model.Student;

public interface IStudentMgmtService {
	
	public long getNumberOfStudent();
	
	public String registerStudent(Student student);
	
	public Iterable<Student> getAllStudents();
	
	public Student getStudentById(long id);
	
	public List<Student> getByRegdNumber(long regdno);
	
	public List<Student> getByRoomNumber(String roomno);
}
