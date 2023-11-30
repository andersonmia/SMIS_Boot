package rw.ac.rca.bootrca.services;

import rw.ac.rca.bootrca.DTO.MarksDTO;
import rw.ac.rca.bootrca.models.Marks;

import java.util.List;
import java.util.UUID;

public interface MarksService {
    List<Marks> listMarksByCourse(UUID courseID);
    List<Marks> listMarksByStudent(UUID studentID);
    Marks addMarks(MarksDTO marksDTO);
    Marks updateMarks(UUID marksID, MarksDTO marksDTO);
    void deleteMarks(UUID marksID);
}
