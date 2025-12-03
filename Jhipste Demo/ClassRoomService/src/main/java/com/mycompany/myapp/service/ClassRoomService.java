package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ClassRoomDTO;
import com.mycompany.myapp.service.dto.ClassRoomRequest;
import com.mycompany.myapp.service.dto.ClassRoomResponseId;
import com.mycompany.myapp.service.dto.ClassRoomResponseTc;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ClassRoom}.
 */
public interface ClassRoomService {
    /**
     * Save a classRoom.
     *
     * @param classRoomDTO the entity to save.
     * @return the persisted entity.
     */
    ClassRoomResponseTc save(ClassRoomRequest request);

    /**
     * Updates a classRoom.
     *
     * @param classRoomDTO the entity to update.
     * @return the persisted entity.
     */
    ClassRoomDTO update(ClassRoomRequest request);

    /**
     * Partially updates a classRoom.
     *
     * @param classRoomDTO the entity to update partially.
     * @return the persisted entity.
     */
    ClassRoomResponseTc partialUpdate(ClassRoomRequest request, Long id);

    /**
     * Get all the classRooms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClassRoomDTO> findAll(Pageable pageable);

    /**
     * Get the "id" classRoom.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    ClassRoomResponseId findOne(Long id);

    /**
     * Delete the "id" classRoom.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
