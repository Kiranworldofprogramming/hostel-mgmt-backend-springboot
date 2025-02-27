package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nt.model.Student;
import com.nt.repository.IStudentRepo;
import com.nt.repository.IStudentRepo2;

@Service
public class StudentMgmtServiceImpl implements IStudentMgmtService{
	
	@Autowired
	private IStudentRepo studRepo;
	
	@Autowired
	private IStudentRepo2 studRepo2;
	

	@Override
	public long getNumberOfStudent() {
		return studRepo.count();
	}

	@Override
	public String registerStudent(Student student) {
		Student stud = studRepo.save(student);
		return "Student is saved with id :"+stud.getId();
	}

	@Override
	public Iterable<Student> getAllStudents() {
		List<Student> list = (List<Student>) studRepo.findAll();
		return list;
	}

	@Override
	public Student getStudentById(long id) {
	
		return studRepo.findById(id).orElseThrow(()->new IllegalArgumentException());
	}

	@Override
	public List<Student> getByRegdNumber(long regdNo) {
		
		return studRepo2.findByRegdNo(regdNo);
	}

	@Override
	public List<Student> getByRoomNumber(String roomno) {
		return studRepo2.findByRoomNo(roomno);
	}

	@Override
	public Page<Student> getAllStudents(int page, int size) {
		return studRepo2.findAll(PageRequest.of(page, size));
	}

	@Override
	public Student getDetails(long regdNo) {
		return studRepo2.findByid(regdNo).orElseThrow(() -> new RuntimeException("student not found"));
	}

	@Override
	public void deleteStudent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String uploadPhoto(long regdNo, MultipartFile file) {
		return "";
	}

	
	
	
	
	
}
