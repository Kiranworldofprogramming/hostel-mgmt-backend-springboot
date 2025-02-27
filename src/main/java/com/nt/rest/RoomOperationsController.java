package com.nt.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.Room;
import com.nt.service.IRoomMgmtService;

@CrossOrigin("*")
@RestController
@RequestMapping("/room/api")
public class RoomOperationsController {
	@Autowired
	private IRoomMgmtService roomService;
	
	@PostMapping("/save")
	public ResponseEntity<String> registerRoom(@RequestBody Room room) {
		try {
			String result = roomService.addRoom(room);
			return new ResponseEntity<String>(result,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/report")
	public ResponseEntity<?> fetchAllRooms(){
		try {
			Iterable<Room> rooms=roomService.getAllRooms();
			return new ResponseEntity<Iterable<Room>>(rooms,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/number")
	public ResponseEntity<?> getRoomNo(){
		try {
			long number=roomService.getNumberOfRooms();
			return new ResponseEntity<Long>(number,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/find/{roomno}")
	public ResponseEntity<?> displayRoomByRoomNo(@PathVariable("roomno")String roomNo){
		try {
			Room room = roomService.fetchRoomByRoomNo(roomNo);
			return new ResponseEntity<Room>(room,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/room/isAvailable/{roomno}")
	public ResponseEntity<?> displayIsRoomAvailable(@PathVariable("roomno")String roomNo){
		try {
			String msg = roomService.isRoomAvailable(roomNo);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/page/{pageSize}/{pageNo}")
	public ResponseEntity<?> paginationOfRoom(@PathVariable("pageSize")int size, @PathVariable("pageNo")int num){
		try {
			Page<Room> page=roomService.generateReport(num-1, size, true, "roomNo");
			if(!page.isEmpty()) {
				System.out.println("Inside if condition");
				List<Room> list=page.getContent();
				return new ResponseEntity<List<Room>>(list,HttpStatus.OK);
			}else {
				System.out.println("Inside else condition");
				return new ResponseEntity<String>("Page not found",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
