package com.ep.backend.services.Impl.successStory;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ep.backend.Exception.ResourceNotFoundException;
import com.ep.backend.dto.successStory.IbdpResultDto;
import com.ep.backend.dto.successStory.IbdpResultDtoToEntity;
import com.ep.backend.entities.successStory.IbdpResult;
import com.ep.backend.repositories.successStory.IbdpResultRepo;
import com.ep.backend.service.successStory.IbdpResultService;

@Service
public class IbdpResultServiceImpl implements IbdpResultService {

    private final IbdpResultRepo ibdpResultRepo;
    private final IbdpResultDtoToEntity ibdpResultDtoToEntity;

    public IbdpResultServiceImpl(IbdpResultRepo ibdpResultRepo, IbdpResultDtoToEntity ibdpResultDtoToEntity) {
        this.ibdpResultRepo = ibdpResultRepo;
        this.ibdpResultDtoToEntity = ibdpResultDtoToEntity;
    }

    @Override
    public IbdpResultDto addIbdpResult(IbdpResultDto ibdpResultDto) {
        IbdpResult ibdpResult = ibdpResultDtoToEntity.dtoToIbdpResult(ibdpResultDto);
        ibdpResult = ibdpResultRepo.save(ibdpResult);
        ibdpResultDto = ibdpResultDtoToEntity.ibpdResultToDto(ibdpResult);
        return ibdpResultDto;
    }


    @Override
   public List<IbdpResultDto> getAllIbdpResults() {
        List<IbdpResult> ibdpResults = this.ibdpResultRepo.findAll();
        List<IbdpResultDto> ibdpResultDto = ibdpResults.stream()
                .map(ibdpResult -> this.ibdpResultDtoToEntity.ibpdResultToDto(ibdpResult))
                .collect(Collectors.toList());
        return ibdpResultDto;
    }
    
    @Override
    public List<IbdpResultDto> findByStudentName(String studentName) {
    	Optional<IbdpResult> ibdpResults = this.ibdpResultRepo.findByStudentName(studentName);
    	List<IbdpResultDto> ibdpResultDto = ibdpResults.stream()
                .map(ibdpResult -> this.ibdpResultDtoToEntity.ibpdResultToDto(ibdpResult))
                .collect(Collectors.toList());
    	return ibdpResultDto;
    }
    
    @Override
    public IbdpResultDto patchIbdpResult(IbdpResultDto ibdpResultDto, String studentName) throws ResourceNotFoundException {
        Optional<IbdpResult> ibdpResultOptional = this.ibdpResultRepo.findByStudentName(studentName);
        
        if (ibdpResultOptional.isPresent()) {
            IbdpResult updatedResult = ibdpResultOptional.get();
            
            // Update fields if they are not null in the DTO
            if (ibdpResultDto.getStudentName() != null) {
                updatedResult.setStudentName(ibdpResultDto.getStudentName());
            }
            if (ibdpResultDto.getSchool_Name() != null) {
                updatedResult.setSchool_Name(ibdpResultDto.getSchool_Name());
            }
            if (ibdpResultDto.getLevel() != null) {
                updatedResult.setLevel(ibdpResultDto.getLevel());
            }
            if (ibdpResultDto.getScore() != null) {
                updatedResult.setScore(ibdpResultDto.getScore());
            }
            // Assuming `status` is a boolean field
            updatedResult.setStatus(ibdpResultDto.isStatus());
            
            // Save the updated entity
            this.ibdpResultRepo.save(updatedResult);
            
            // Convert the updated entity to DTO and return it
            return ibdpResultDtoToEntity.ibpdResultToDto(updatedResult);
        } else {
            // Handle the case when no matching entity is found
            // You can return a message or throw an exception, depending on your application's requirements
            String errorMessage = "IbdpResult with studentName '" + studentName + "' not available";
            // You can also create a custom exception class and throw it here if needed
            throw new ResourceNotFoundException(errorMessage);
        }
    }



    @Override
    public boolean deleteAllIbdpResult(){
    	List<IbdpResult> deleteIbdpResults = this.ibdpResultRepo.findAll();
    	if (deleteIbdpResults.isEmpty()) {
    		return false;
    	}
    	else this.ibdpResultRepo.deleteAll(deleteIbdpResults);
    	return true;
    }

    
    @Override
    public boolean deleteSingleIbdpResult(String studentName) {
        // Retrieve the list of IbdpResult entities with the given studentName
        Optional<IbdpResult> ibdpResultsToDelete = this.ibdpResultRepo.findByStudentName(studentName);

        // Check if any matching entities were found
        if (ibdpResultsToDelete.isPresent()) {
            // Assuming you want to delete the first matching entity, you can do:
            IbdpResult ibdpResultToDelete = ibdpResultsToDelete.get(); // Get the first matching entity
            this.ibdpResultRepo.delete(ibdpResultToDelete); // Delete the entity

            // Return true to indicate that a matching entity was deleted
            return true;
        } else {
            // Return false to indicate that no matching entity was found and deleted
            return false;
        }
    }



    
//    @Override
//    public IbdpResultDto updateIbdpResult(IbdpResultDto ibdpResultDto, String studentName) {
//        List<IbdpResult> ibdpResult = this.ibdpResultRepo.findByStudentName(studentName);
//        if (ibdpResult == null) {
//            throw new ResourceNotFoundException("IbdpResult", "Student_name", studentName);
//       }
//
//        ibdpResult.setStudentName(ibdpResultDto.getStudentName());
//        ibdpResult.setSchool_Name(ibdpResultDto.getSchool_Name());
//        ibdpResult.setYear(ibdpResultDto.getYear());
//        ibdpResult.setLevel(ibdpResultDto.getLevel());
//        ibdpResult.setScore(ibdpResultDto.getScore());
//        ibdpResult.setIA_Score(ibdpResultDto.getIA_Score());
//        ibdpResult.setStatus(ibdpResultDto.isStatus());
//
//        IbdpResult updatedIbdpResult = this.ibdpResultRepo.save(ibdpResult);
//        IbdpResultDto ibdpResultDto1 = this.ibdpResultDtoToEntity.ibpdResultToDto(updatedIbdpResult);
//        return ibdpResultDto1;
//    }
    
    
  
//    @Override
//    public IbdpResultDto updateIbdpResult(IbdpResultDto ibdpResultDto, String studentName) {
//        // Find the IbdpResult entity by studentName
//        List<IbdpResult> ibdpResults = this.ibdpResultRepo.findByStudentName(studentName);
//
//        // Check if any matching entities were found
//        if (ibdpResults.isEmpty()) {
//            throw new ResourceNotFoundException("IbdpResult", "studentName", studentName);
//        }
//
//        // Assuming you want to update the first matching entity, you can do:
//        IbdpResult ibdpResultToUpdate = (IbdpResult) this.ibdpResultRepo.findByStudentName(studentName);
//
//        // Update the entity with values from the DTO
//        ibdpResultToUpdate.setStudentName(ibdpResultDto.getStudentName());
//        ibdpResultToUpdate.setSchool_Name(ibdpResultDto.getSchool_Name());
//        ibdpResultToUpdate.setYear(ibdpResultDto.getYear());
//        ibdpResultToUpdate.setLevel(ibdpResultDto.getLevel());
//        ibdpResultToUpdate.setScore(ibdpResultDto.getScore());
//        ibdpResultToUpdate.setIA_Score(ibdpResultDto.getIA_Score());
//        ibdpResultToUpdate.setStatus(ibdpResultDto.isStatus());
//
//        // Save the updated entity
//        IbdpResult updatedIbdpResult = this.ibdpResultRepo.save(ibdpResultToUpdate);
//
//        // Convert the updated entity back to DTO and return it
//        return this.ibdpResultDtoToEntity.ibpdResultToDto(updatedIbdpResult);
//    }

//   
//    @Override
//    public IbdpResultDto updateIbdpResult(IbdpResultDto ibdpResultDto, String studentName) {
//        // Find the IbdpResult entity by studentName
//        Optional<IbdpResult> ibdpResultOptional = this.ibdpResultRepo.findByStudentName(studentName);
//
//        if (!ibdpResultOptional.isPresent()) {
//            throw new ResourceNotFoundException("IbdpResult", "studentName", studentName);
//        }
//
//        IbdpResult ibdpResultToUpdate = ibdpResultOptional.get();
//
//        // Update the entity fields with the values from ibdpResultDto
//        ibdpResultToUpdate.setStudentName(ibdpResultDto.getStudentName());
//        ibdpResultToUpdate.setSchool_Name(ibdpResultDto.getSchool_Name());
//        ibdpResultToUpdate.setYear(ibdpResultDto.getYear());
//        ibdpResultToUpdate.setLevel(ibdpResultDto.getLevel());
//        ibdpResultToUpdate.setScore(ibdpResultDto.getScore());
//        ibdpResultToUpdate.setIA_Score(ibdpResultDto.getIA_Score());
//        ibdpResultToUpdate.setStatus(ibdpResultDto.isStatus());
//
//        // Save the updated entity
//        this.ibdpResultRepo.save(ibdpResultToUpdate);
//
//        // Convert the updated entity to IbdpResultDto
//        return ibdpResultDtoToEntity.ibpdResultToDto(ibdpResultToUpdate);
//    }
//    else {
//      String studentName;
//	String errorMessage = "IbdpResult with studentName '" + studentName + "' not available";
//      throw new ResourceNotFoundException(errorMessage, errorMessage, errorMessage);
//
//    }

    
    
  @Override
  public IbdpResultDto updateIbdpResult(IbdpResultDto ibdpResultDto, String studentName) throws ResourceNotFoundException {
      // Find the IbdpResult entity by studentName
      Optional<IbdpResult> ibdpResultOptional = this.ibdpResultRepo.findByStudentName(studentName);

      if (ibdpResultOptional.isPresent()) {
          IbdpResult ibdpResultToUpdate = ibdpResultOptional.get();

          // Update the entity fields with the values from ibdpResultDto
          ibdpResultToUpdate.setStudentName(ibdpResultDto.getStudentName());
          ibdpResultToUpdate.setSchool_Name(ibdpResultDto.getSchool_Name());
          ibdpResultToUpdate.setYear(ibdpResultDto.getYear());
          ibdpResultToUpdate.setLevel(ibdpResultDto.getLevel());
          ibdpResultToUpdate.setScore(ibdpResultDto.getScore());
          ibdpResultToUpdate.setIA_Score(ibdpResultDto.getIA_Score());
          ibdpResultToUpdate.setStatus(ibdpResultDto.isStatus());

          // Save the updated entity
          this.ibdpResultRepo.save(ibdpResultToUpdate);

          // Convert the updated entity to IbdpResultDto
          return ibdpResultDtoToEntity.ibpdResultToDto(ibdpResultToUpdate);
      } else {
          // Handle the case when no matching entity is found
          // You can return a message or throw an exception, depending on your application's requirements
          String errorMessage = "IbdpResult with studentName '" + studentName + "' not available";
          // You can also create a custom exception class and throw it here if needed
          throw new ResourceNotFoundException(errorMessage);
      }
   }
  
  
}