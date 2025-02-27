package com.nt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.nt.model.Student;

public interface IStudentMgmtService {
	
	public long getNumberOfStudent();
	
	public String registerStudent(Student student);
	
	public Iterable<Student> getAllStudents();
	
	public Student getStudentById(long id);
	
	public List<Student> getByRegdNumber(long regdno);
	
	public List<Student> getByRoomNumber(String roomno);
	
	public Page<Student> getAllStudents(int page, int size);
	
	public Student getDetails(long regdNo);
	
	public void deleteStudent();
	
	public String uploadPhoto(long regdNo, MultipartFile file);
}
