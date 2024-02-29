package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.nt.model.Student;
import com.nt.service.IStudentMgmtService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/student/api")
public class StudentOperationsController {
	
	@Autowired
	private IStudentMgmtService studService;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<String> saveStudent(@RequestBody Student student){
		try {
			String msg = studService.registerStudent(student);
			return new ResponseEntity<String>(msg,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@GetMapping("/report")
	public ResponseEntity<?> fetchAllStudents(){
		try {
			Iterable<Student> list=studService.getAllStudents();
			return new ResponseEntity<Iterable<Student>>(list,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> fetchStudentId(@PathVariable long id){
		try {
			Student stud = studService.getStudentById(id);
			return new ResponseEntity<Student>(stud,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get/regdno/{regdno}")
	public ResponseEntity<?> fetchStudentByRegdNo(@PathVariable long regdno){
		try {
	    List<Student> student = studService.getByRegdNumber(regdno);
	    return new ResponseEntity<List<Student>>(student,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get/roomno/{roomno}")
	public ResponseEntity<?> fetchStudentByRoomNo(@PathVariable String roomno){
		try {
	    List<Student> student = studService.getByRoomNumber(roomno);
	    return new ResponseEntity<List<Student>>(student,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/number")
	public ResponseEntity<?> getStudentNo(){
		try {
			long number = studService.getNumberOfStudent();
			return new ResponseEntity<Long>(number,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
}
