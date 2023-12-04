package rw.ac.rca.bootrca.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.bootrca.DTO.InstructorDTO;
import rw.ac.rca.bootrca.exceptions.InstructorNotFoundException;
import rw.ac.rca.bootrca.models.Instructor;
import rw.ac.rca.bootrca.repositories.InstructorRepository;
import rw.ac.rca.bootrca.services.InstructorService;
import rw.ac.rca.bootrca.utils.DateParser;
import rw.ac.rca.bootrca.utils.PhoneNumberProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InstructorServiceImpl extends DateParser implements InstructorService {
    final InstructorRepository instructorRepository;


    @Override
    public Instructor createInstructor(InstructorDTO instructorDTO) throws ParseException {
        PhoneNumberProcessor phoneNumberProcessor = new PhoneNumberProcessor();
        String phoneNumber = phoneNumberProcessor.formatPhoneNumber(instructorDTO.getCountryCode(), instructorDTO.getPhoneNumber());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = simpleDateFormat.parse(instructorDTO.getDateOfBirth());

        Instructor instructor = new Instructor();
        instructor.setLastName(instructorDTO.getLastName());
        instructor.setGender(instructorDTO.getGender());
        instructor.setFirstName(instructor.getFirstName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setPhoneNumber(phoneNumber);
        instructor.setDateOfBirth(dateOfBirth);

        return instructorRepository.save(instructor);
    }

    @Override
    public List<Instructor> listAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor updateInstructor(UUID instructorID, InstructorDTO instructorDTO) throws ParseException {
        Instructor existingInstructor = instructorRepository.findById(instructorID)
                .orElseThrow(
                        () -> new InstructorNotFoundException("Instructor"+instructorID+" Not Found")
                );

        if (instructorDTO.getFirstName() != null)
            existingInstructor.setFirstName(instructorDTO.getFirstName());

        if (instructorDTO.getLastName() != null)
            existingInstructor.setLastName(instructorDTO.getLastName());

        if (instructorDTO.getGender() != null)
            existingInstructor.setGender(instructorDTO.getGender());

        if (instructorDTO.getEmail() != null)
            existingInstructor.setEmail(instructorDTO.getEmail());

        if (instructorDTO.getDateOfBirth() != null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            existingInstructor.setDateOfBirth(simpleDateFormat.parse(instructorDTO.getDateOfBirth()));
        }


        if (instructorDTO.getCountryCode() != 0 && instructorDTO.getPhoneNumber() != 0){
            PhoneNumberProcessor phoneNumberProcessor = new PhoneNumberProcessor();
            String phoneNumber = phoneNumberProcessor.formatPhoneNumber(instructorDTO.getCountryCode(), instructorDTO.getPhoneNumber());
            existingInstructor.setPhoneNumber(phoneNumber);
        }
        return instructorRepository.save(existingInstructor);
    }

    @Override
    public void deleteInstructor(UUID instructorID) {
        Instructor existingInstructor = instructorRepository.findById(instructorID)
                .orElseThrow(
                        () -> new InstructorNotFoundException("Instructor"+instructorID+" Not Found")
                );
        instructorRepository.delete(existingInstructor);

    }
}
