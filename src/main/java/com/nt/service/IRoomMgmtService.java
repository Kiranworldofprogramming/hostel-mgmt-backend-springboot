package com.nt.service;

import org.springframework.data.domain.Page;

import com.nt.exception.RoomNotFoundException;
import com.nt.model.Room;

public interface IRoomMgmtService {
	
	public String addRoom(Room room);
	
	public Iterable<Room> getAllRooms();
	
	public long getNumberOfRooms();
	
	public Room fetchRoomByRoomNo(String roomNo) throws RoomNotFoundException;
	
	public String isRoomAvailable(String roomNo);
	
	public Page<Room> generateReport(int pageNo,int pageSize,boolean asc,String...props);
	
	public String getTime();

}
