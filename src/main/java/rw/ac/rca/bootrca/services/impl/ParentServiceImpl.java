package rw.ac.rca.bootrca.services.impl;

import org.springframework.stereotype.Service;
import rw.ac.rca.bootrca.DTO.ParentDTO;
import rw.ac.rca.bootrca.exceptions.ParentNotFoundException;
import rw.ac.rca.bootrca.models.Parent;
import rw.ac.rca.bootrca.repositories.ParentRepository;
import rw.ac.rca.bootrca.services.ParentService;
import rw.ac.rca.bootrca.utils.PhoneNumberProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ParentServiceImpl implements ParentService {
    final ParentRepository parentRepository;

    public ParentServiceImpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public List<Parent> listAllParents() {
        return parentRepository.findAll();
    }

    @Override
    public Parent createParent(ParentDTO parentDTO) throws ParseException {
        PhoneNumberProcessor phoneNumberProcessor = new PhoneNumberProcessor();
        String phoneNumber = phoneNumberProcessor.formatPhoneNumber(parentDTO.getCountryCode(), parentDTO.getPhoneNumber());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = simpleDateFormat.parse(parentDTO.getDateOfBirth());

        Parent newParent = new Parent();
        newParent.setFirstName(parentDTO.getFirstName());
        newParent.setLastName(parentDTO.getLastName());
        newParent.setGender(parentDTO.getGender());
        newParent.setEmail(parentDTO.getEmail());
        newParent.setDateOfBirth(dateOfBirth);
        newParent.setPhoneNumber(phoneNumber);

        return parentRepository.save(newParent);
    }

    @Override
    public Parent updateParent(UUID parentID, ParentDTO parentDTO) throws ParseException {
        Parent existingParent = parentRepository.findById(parentID)
                .orElseThrow(
                        () -> new ParentNotFoundException("Parent "+parentID+" Not Found")
                );

        if (parentDTO.getFirstName() != null)
            existingParent.setFirstName(parentDTO.getFirstName());
        if (parentDTO.getLastName() != null)
            existingParent.setLastName(parentDTO.getLastName());
        if (parentDTO.getGender() != null)
            existingParent.setGender(existingParent.getGender());
        if (parentDTO.getEmail() != null)
            existingParent.setEmail(parentDTO.getEmail());
        if (parentDTO.getDateOfBirth() != null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            existingParent.setDateOfBirth(simpleDateFormat.parse(parentDTO.getDateOfBirth()));
        }
        if (parentDTO.getCountryCode() != 0 && parentDTO.getPhoneNumber() != 0)
            existingParent.setPhoneNumber("+" +parentDTO.getCountryCode()+ " " +parentDTO.getPhoneNumber());

        return parentRepository.save(existingParent);
    }
}
