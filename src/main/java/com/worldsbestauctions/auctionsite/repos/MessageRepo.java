package com.worldsbestauctions.auctionsite.repos;

import com.worldsbestauctions.auctionsite.entities.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
    Iterable<Message> findMessageBySenderidOrReceiverid(long s, long r);
    Iterable<Message> findMessageBySenderidAndReceiveridAndHasread(long s, long r, short i);
}
