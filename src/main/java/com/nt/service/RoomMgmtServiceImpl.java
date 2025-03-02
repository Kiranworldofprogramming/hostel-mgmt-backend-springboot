package com.nt.service;



import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nt.exception.RoomNotFoundException;
import com.nt.model.Room;
import com.nt.repository.IRoomPagingRepo;
import com.nt.repository.IRoomRepo;

@Service
public class RoomMgmtServiceImpl implements IRoomMgmtService {
	
	@Autowired
	private IRoomRepo roomRepo;
	
	@Autowired
	private IRoomPagingRepo pagingRepo;

	@Override
	public String addRoom(Room room) {
		Room rm=roomRepo.save(room);
		return "Room has been successfully registered with id "+rm.getId();
	}

	@Override
	public Iterable<Room> getAllRooms() {
		Iterable<Room> result= roomRepo.findAll();
		return result;
	}

	@Override
	public long getNumberOfRooms() {
		return roomRepo.count();
	}

	@Override
	public Room fetchRoomByRoomNo(String roomNo) throws RoomNotFoundException {
		Room rm = roomRepo.findByRoomNo(roomNo);
		
		if(rm==null)
			throw new RoomNotFoundException(roomNo+" room not found");
		
		return rm;
	}

	@Override
	public String isRoomAvailable(String roomNo) {
		Room rm = roomRepo.findByRoomNo(roomNo);
		
		if(rm==null)
			throw new RoomNotFoundException(roomNo+" room not found");
		
		int maxSize=rm.getMaxCapacity();
		int currentSize=rm.getOccupied();
		
		if(maxSize==currentSize) {
			return "Room is full";
		}else {
			return "Room is available with seat"+currentSize;
		}
		
		
	}

	@Override
	public Page<Room> generateReport(int pageNo, int pageSize, boolean asc, String... props) {
		Pageable pageable=PageRequest.of(pageNo, pageSize,Sort.by(asc?Direction.ASC:Direction.DESC, props));
		Page<Room> page=pagingRepo.findAll(pageable);
		return page;
	}

	@Override
	@Scheduled(fixedRate = 3000)
	public String getTime() {
		System.out.print("Method is called");
		return "Current time : " + LocalTime.now();
	}

	
	

	
}
