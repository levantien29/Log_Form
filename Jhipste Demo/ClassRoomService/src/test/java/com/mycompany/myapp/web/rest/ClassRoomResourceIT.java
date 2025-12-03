// package com.mycompany.myapp.web.rest;

// import static com.mycompany.myapp.domain.ClassRoomAsserts.*;
// import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
// import static org.assertj.core.api.Assertions.assertThat;
// import static org.hamcrest.Matchers.hasItem;
// import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.mycompany.myapp.IntegrationTest;
// import com.mycompany.myapp.domain.ClassRoom;
// import com.mycompany.myapp.repository.ClassRoomRepository;
// import com.mycompany.myapp.service.dto.ClassRoomDTO;
// import com.mycompany.myapp.service.mapper.ClassRoomMapper;
// import jakarta.persistence.EntityManager;
// import java.util.Random;
// import java.util.concurrent.atomic.AtomicLong;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.http.MediaType;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.transaction.annotation.Transactional;

// /**
//  * Integration tests for the {@link ClassRoomResource} REST controller.
//  */
// @IntegrationTest
// @AutoConfigureMockMvc
// @WithMockUser
// class ClassRoomResourceIT {

//     private static final String DEFAULT_CLASS_NAME = "AAAAAAAAAA";
//     private static final String UPDATED_CLASS_NAME = "BBBBBBBBBB";

//     private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
//     private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

//     private static final Integer DEFAULT_YEAR = 1;
//     private static final Integer UPDATED_YEAR = 2;

//     private static final Long DEFAULT_TEACHER_ID = 1L;
//     private static final Long UPDATED_TEACHER_ID = 2L;

//     private static final String ENTITY_API_URL = "/api/class-rooms";
//     private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

//     private static Random random = new Random();
//     private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

//     @Autowired
//     private ObjectMapper om;

//     @Autowired
//     private ClassRoomRepository classRoomRepository;

//     @Autowired
//     private ClassRoomMapper classRoomMapper;

//     @Autowired
//     private EntityManager em;

//     @Autowired
//     private MockMvc restClassRoomMockMvc;

//     private ClassRoom classRoom;

//     private ClassRoom insertedClassRoom;

//     /**
//      * Create an entity for this test.
//      *
//      * This is a static method, as tests for other entities might also need it,
//      * if they test an entity which requires the current entity.
//      */
//     public static ClassRoom createEntity() {
//         return new ClassRoom()
//             .className(DEFAULT_CLASS_NAME)
//             .department(DEFAULT_DEPARTMENT)
//             .year(DEFAULT_YEAR)
//             .teacherId(DEFAULT_TEACHER_ID);
//     }

//     /**
//      * Create an updated entity for this test.
//      *
//      * This is a static method, as tests for other entities might also need it,
//      * if they test an entity which requires the current entity.
//      */
//     public static ClassRoom createUpdatedEntity() {
//         return new ClassRoom()
//             .className(UPDATED_CLASS_NAME)
//             .department(UPDATED_DEPARTMENT)
//             .year(UPDATED_YEAR)
//             .teacherId(UPDATED_TEACHER_ID);
//     }

//     @BeforeEach
//     void initTest() {
//         classRoom = createEntity();
//     }

//     @AfterEach
//     void cleanup() {
//         if (insertedClassRoom != null) {
//             classRoomRepository.delete(insertedClassRoom);
//             insertedClassRoom = null;
//         }
//     }

//     @Test
//     @Transactional
//     void createClassRoom() throws Exception {
//         long databaseSizeBeforeCreate = getRepositoryCount();
//         // Create the ClassRoom
//         ClassRoomDTO classRoomDTO = classRoomMapper.toDto(classRoom);
//         var returnedClassRoomDTO = om.readValue(
//             restClassRoomMockMvc
//                 .perform(
//                     post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(classRoomDTO))
//                 )
//                 .andExpect(status().isCreated())
//                 .andReturn()
//                 .getResponse()
//                 .getContentAsString(),
//             ClassRoomDTO.class
//         );

//         // Validate the ClassRoom in the database
//         assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
//         var returnedClassRoom = classRoomMapper.toEntity(returnedClassRoomDTO);
//         assertClassRoomUpdatableFieldsEquals(returnedClassRoom, getPersistedClassRoom(returnedClassRoom));

//         insertedClassRoom = returnedClassRoom;
//     }

//     @Test
//     @Transactional
//     void createClassRoomWithExistingId() throws Exception {
//         // Create the ClassRoom with an existing ID
//         classRoom.setId(1L);
//         ClassRoomDTO classRoomDTO = classRoomMapper.toDto(classRoom);

//         long databaseSizeBeforeCreate = getRepositoryCount();

//         // An entity with an existing ID cannot be created, so this API call must fail
//         restClassRoomMockMvc
//             .perform(post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(classRoomDTO)))
//             .andExpect(status().isBadRequest());

//         // Validate the ClassRoom in the database
//         assertSameRepositoryCount(databaseSizeBeforeCreate);
//     }

//     @Test
//     @Transactional
//     void getAllClassRooms() throws Exception {
//         // Initialize the database
//         insertedClassRoom = classRoomRepository.saveAndFlush(classRoom);

//         // Get all the classRoomList
//         restClassRoomMockMvc
//             .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//             .andExpect(status().isOk())
//             .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//             .andExpect(jsonPath("$.[*].id").value(hasItem(classRoom.getId().intValue())))
//             .andExpect(jsonPath("$.[*].className").value(hasItem(DEFAULT_CLASS_NAME)))
//             .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
//             .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
//             .andExpect(jsonPath("$.[*].teacherId").value(hasItem(DEFAULT_TEACHER_ID.intValue())));
//     }

//     @Test
//     @Transactional
//     void getClassRoom() throws Exception {
//         // Initialize the database
//         insertedClassRoom = classRoomRepository.saveAndFlush(classRoom);

//         // Get the classRoom
//         restClassRoomMockMvc
//             .perform(get(ENTITY_API_URL_ID, classRoom.getId()))
//             .andExpect(status().isOk())
//             .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//             .andExpect(jsonPath("$.id").value(classRoom.getId().intValue()))
//             .andExpect(jsonPath("$.className").value(DEFAULT_CLASS_NAME))
//             .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
//             .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
//             .andExpect(jsonPath("$.teacherId").value(DEFAULT_TEACHER_ID.intValue()));
//     }

//     @Test
//     @Transactional
//     void getNonExistingClassRoom() throws Exception {
//         // Get the classRoom
//         restClassRoomMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//     }

//     @Test
//     @Transactional
//     void putExistingClassRoom() throws Exception {
//         // Initialize the database
//         insertedClassRoom = classRoomRepository.saveAndFlush(classRoom);

//         long databaseSizeBeforeUpdate = getRepositoryCount();

//         // Update the classRoom
//         ClassRoom updatedClassRoom = classRoomRepository.findById(classRoom.getId()).orElseThrow();
//         // Disconnect from session so that the updates on updatedClassRoom are not directly saved in db
//         em.detach(updatedClassRoom);
//         updatedClassRoom.className(UPDATED_CLASS_NAME).department(UPDATED_DEPARTMENT).year(UPDATED_YEAR).teacherId(UPDATED_TEACHER_ID);
//         ClassRoomDTO classRoomDTO = classRoomMapper.toDto(updatedClassRoom);

//         restClassRoomMockMvc
//             .perform(
//                 put(ENTITY_API_URL_ID, classRoomDTO.getId())
//                     .with(csrf())
//                     .contentType(MediaType.APPLICATION_JSON)
//                     .content(om.writeValueAsBytes(classRoomDTO))
//             )
//             .andExpect(status().isOk());

//         // Validate the ClassRoom in the database
//         assertSameRepositoryCount(databaseSizeBeforeUpdate);
//         assertPersistedClassRoomToMatchAllProperties(updatedClassRoom);
//     }

//     @Test
//     @Transactional
//     void putNonExistingClassRoom() throws Exception {
//         long databaseSizeBeforeUpdate = getRepositoryCount();
//         classRoom.setId(longCount.incrementAndGet());

//         // Create the ClassRoom
//         ClassRoomDTO classRoomDTO = classRoomMapper.toDto(classRoom);

//         // If the entity doesn't have an ID, it will throw BadRequestAlertException
//         restClassRoomMockMvc
//             .perform(
//                 put(ENTITY_API_URL_ID, classRoomDTO.getId())
//                     .with(csrf())
//                     .contentType(MediaType.APPLICATION_JSON)
//                     .content(om.writeValueAsBytes(classRoomDTO))
//             )
//             .andExpect(status().isBadRequest());

//         // Validate the ClassRoom in the database
//         assertSameRepositoryCount(databaseSizeBeforeUpdate);
//     }

//     @Test
//     @Transactional
//     void putWithIdMismatchClassRoom() throws Exception {
//         long databaseSizeBeforeUpdate = getRepositoryCount();
//         classRoom.setId(longCount.incrementAndGet());

//         // Create the ClassRoom
//         ClassRoomDTO classRoomDTO = classRoomMapper.toDto(classRoom);

//         // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//         restClassRoomMockMvc
//             .perform(
//                 put(ENTITY_API_URL_ID, longCount.incrementAndGet())
//                     .with(csrf())
//                     .contentType(MediaType.APPLICATION_JSON)
//                     .content(om.writeValueAsBytes(classRoomDTO))
//             )
//             .andExpect(status().isBadRequest());

//         // Validate the ClassRoom in the database
//         assertSameRepositoryCount(databaseSizeBeforeUpdate);
//     }

//     @Test
//     @Transactional
//     void putWithMissingIdPathParamClassRoom() throws Exception {
//         long databaseSizeBeforeUpdate = getRepositoryCount();
//         classRoom.setId(longCount.incrementAndGet());

//         // Create the ClassRoom
//         ClassRoomDTO classRoomDTO = classRoomMapper.toDto(classRoom);

//         // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//         restClassRoomMockMvc
//             .perform(put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(classRoomDTO)))
//             .andExpect(status().isMethodNotAllowed());

//         // Validate the ClassRoom in the database
//         assertSameRepositoryCount(databaseSizeBeforeUpdate);
//     }

//     @Test
//     @Transactional
//     void partialUpdateClassRoomWithPatch() throws Exception {
//         // Initialize the database
//         insertedClassRoom = classRoomRepository.saveAndFlush(classRoom);

//         long databaseSizeBeforeUpdate = getRepositoryCount();

//         // Update the classRoom using partial update
//         ClassRoom partialUpdatedClassRoom = new ClassRoom();
//         partialUpdatedClassRoom.setId(classRoom.getId());

//         partialUpdatedClassRoom.department(UPDATED_DEPARTMENT).year(UPDATED_YEAR);

//         restClassRoomMockMvc
//             .perform(
//                 patch(ENTITY_API_URL_ID, partialUpdatedClassRoom.getId())
//                     .with(csrf())
//                     .contentType("application/merge-patch+json")
//                     .content(om.writeValueAsBytes(partialUpdatedClassRoom))
//             )
//             .andExpect(status().isOk());

//         // Validate the ClassRoom in the database

//         assertSameRepositoryCount(databaseSizeBeforeUpdate);
//         assertClassRoomUpdatableFieldsEquals(
//             createUpdateProxyForBean(partialUpdatedClassRoom, classRoom),
//             getPersistedClassRoom(classRoom)
//         );
//     }

//     @Test
//     @Transactional
//     void fullUpdateClassRoomWithPatch() throws Exception {
//         // Initialize the database
//         insertedClassRoom = classRoomRepository.saveAndFlush(classRoom);

//         long databaseSizeBeforeUpdate = getRepositoryCount();

//         // Update the classRoom using partial update
//         ClassRoom partialUpdatedClassRoom = new ClassRoom();
//         partialUpdatedClassRoom.setId(classRoom.getId());

//         partialUpdatedClassRoom
//             .className(UPDATED_CLASS_NAME)
//             .department(UPDATED_DEPARTMENT)
//             .year(UPDATED_YEAR)
//             .teacherId(UPDATED_TEACHER_ID);

//         restClassRoomMockMvc
//             .perform(
//                 patch(ENTITY_API_URL_ID, partialUpdatedClassRoom.getId())
//                     .with(csrf())
//                     .contentType("application/merge-patch+json")
//                     .content(om.writeValueAsBytes(partialUpdatedClassRoom))
//             )
//             .andExpect(status().isOk());

//         // Validate the ClassRoom in the database

//         assertSameRepositoryCount(databaseSizeBeforeUpdate);
//         assertClassRoomUpdatableFieldsEquals(partialUpdatedClassRoom, getPersistedClassRoom(partialUpdatedClassRoom));
//     }

//     @Test
//     @Transactional
//     void patchNonExistingClassRoom() throws Exception {
//         long databaseSizeBeforeUpdate = getRepositoryCount();
//         classRoom.setId(longCount.incrementAndGet());

//         // Create the ClassRoom
//         ClassRoomDTO classRoomDTO = classRoomMapper.toDto(classRoom);

//         // If the entity doesn't have an ID, it will throw BadRequestAlertException
//         restClassRoomMockMvc
//             .perform(
//                 patch(ENTITY_API_URL_ID, classRoomDTO.getId())
//                     .with(csrf())
//                     .contentType("application/merge-patch+json")
//                     .content(om.writeValueAsBytes(classRoomDTO))
//             )
//             .andExpect(status().isBadRequest());

//         // Validate the ClassRoom in the database
//         assertSameRepositoryCount(databaseSizeBeforeUpdate);
//     }

//     @Test
//     @Transactional
//     void patchWithIdMismatchClassRoom() throws Exception {
//         long databaseSizeBeforeUpdate = getRepositoryCount();
//         classRoom.setId(longCount.incrementAndGet());

//         // Create the ClassRoom
//         ClassRoomDTO classRoomDTO = classRoomMapper.toDto(classRoom);

//         // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//         restClassRoomMockMvc
//             .perform(
//                 patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
//                     .with(csrf())
//                     .contentType("application/merge-patch+json")
//                     .content(om.writeValueAsBytes(classRoomDTO))
//             )
//             .andExpect(status().isBadRequest());

//         // Validate the ClassRoom in the database
//         assertSameRepositoryCount(databaseSizeBeforeUpdate);
//     }

//     @Test
//     @Transactional
//     void patchWithMissingIdPathParamClassRoom() throws Exception {
//         long databaseSizeBeforeUpdate = getRepositoryCount();
//         classRoom.setId(longCount.incrementAndGet());

//         // Create the ClassRoom
//         ClassRoomDTO classRoomDTO = classRoomMapper.toDto(classRoom);

//         // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//         restClassRoomMockMvc
//             .perform(
//                 patch(ENTITY_API_URL).with(csrf()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(classRoomDTO))
//             )
//             .andExpect(status().isMethodNotAllowed());

//         // Validate the ClassRoom in the database
//         assertSameRepositoryCount(databaseSizeBeforeUpdate);
//     }

//     @Test
//     @Transactional
//     void deleteClassRoom() throws Exception {
//         // Initialize the database
//         insertedClassRoom = classRoomRepository.saveAndFlush(classRoom);

//         long databaseSizeBeforeDelete = getRepositoryCount();

//         // Delete the classRoom
//         restClassRoomMockMvc
//             .perform(delete(ENTITY_API_URL_ID, classRoom.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
//             .andExpect(status().isNoContent());

//         // Validate the database contains one less item
//         assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
//     }

//     protected long getRepositoryCount() {
//         return classRoomRepository.count();
//     }

//     protected void assertIncrementedRepositoryCount(long countBefore) {
//         assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
//     }

//     protected void assertDecrementedRepositoryCount(long countBefore) {
//         assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
//     }

//     protected void assertSameRepositoryCount(long countBefore) {
//         assertThat(countBefore).isEqualTo(getRepositoryCount());
//     }

//     protected ClassRoom getPersistedClassRoom(ClassRoom classRoom) {
//         return classRoomRepository.findById(classRoom.getId()).orElseThrow();
//     }

//     protected void assertPersistedClassRoomToMatchAllProperties(ClassRoom expectedClassRoom) {
//         assertClassRoomAllPropertiesEquals(expectedClassRoom, getPersistedClassRoom(expectedClassRoom));
//     }

//     protected void assertPersistedClassRoomToMatchUpdatableProperties(ClassRoom expectedClassRoom) {
//         assertClassRoomAllUpdatablePropertiesEquals(expectedClassRoom, getPersistedClassRoom(expectedClassRoom));
//     }
// }
