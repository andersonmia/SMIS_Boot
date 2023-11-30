package rw.ac.rca.bootrca.services;

import rw.ac.rca.bootrca.DTO.ParentDTO;
import rw.ac.rca.bootrca.models.Parent;

import java.util.List;
import java.util.UUID;

public interface ParentService {
    List<Parent> listAllParents();
    Parent createParent(ParentDTO parentDTO);
    Parent updateParent(UUID parentID, ParentDTO parentDTO);
}
