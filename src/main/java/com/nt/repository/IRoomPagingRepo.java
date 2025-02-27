package com.nt.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nt.model.Room;

public interface IRoomPagingRepo extends PagingAndSortingRepository<Room, Integer> {

}
