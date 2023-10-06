package ca.mcgill.ecse.hotelmanagementbackend;

import ca.mcgill.ecse.hotelmanagementbackend.entity.*;
import ca.mcgill.ecse.hotelmanagementbackend.enumeration.RoomType;
import ca.mcgill.ecse.hotelmanagementbackend.service.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class RoomServiceTest {
    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;


    @Test
    @Transactional
    void testReadAndWriteRoomObjects() {
        Room room = new Room(RoomType.DELUXE, 800);
        roomService.save(room);
        assertEquals("checks if room object is saved and if can be read", 1, roomService.findAll().size());
    }

    @Test
    @Transactional
    void testReadAndWriteRoomAttributes(){
        Room room = new Room(RoomType.DELUXE, 800);
        roomService.save(room);
        //test read
        assertEquals("checks if room attribute can be read", RoomType.DELUXE, room.getType());
        assertEquals("checks if room attribute can be read", 800, room.getFee());
        // test write
        room.setType(RoomType.REGULAR);
        room.setFee(500);
        roomService.save(room);
        assertEquals("checks if room attribute can be saved", RoomType.REGULAR, room.getType());
        assertEquals("checks if room attribute can be saved", 500, room.getFee());
    }

    @Test
    @Transactional
    void testReadAndWriteReference(){
        Hotel hotel1 = new Hotel();
        hotelService.save(hotel1);
        Hotel hotel2 = new Hotel();
        hotelService.save(hotel2);
        Room room = new Room(RoomType.DELUXE, 800);
        room.setHotel(hotel1);
        roomService.save(room);
        assertEquals("checks if Room can read and write its attribute which is an instance of Hotel class", hotel1, room.getHotel());
        room.setHotel(hotel2);
        roomService.save(room);
        assertEquals("checks if Room can read and write its attribute which is an instance of Hotel class", hotel2, room.getHotel());
    }
}