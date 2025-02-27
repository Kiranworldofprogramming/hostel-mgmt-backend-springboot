package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Room;

public interface IRoomRepo extends JpaRepository<Room, Integer> {
	
	public Room findByRoomNo(String roomNo);
}
