package com.example.learn_connection_of_claasses_ford_atabase.controller;

import com.example.learn_connection_of_claasses_ford_atabase.entity.Faculty;
import com.example.learn_connection_of_claasses_ford_atabase.entity.University;
import com.example.learn_connection_of_claasses_ford_atabase.payload.FacultyDto;
import com.example.learn_connection_of_claasses_ford_atabase.repository.FacultyRepository;
import com.example.learn_connection_of_claasses_ford_atabase.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;

    //    Get for Ministry
    @GetMapping
    public List<Faculty> getFaculties() {
        return facultyRepository.findAll();
    }

    //   Create
    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (exists)
            return "This university with faculty already exists";

        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());

        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent()) {
            return "Such university was not found";
        }
        faculty.setUniversity(optionalUniversity.get());

        facultyRepository.save(faculty);
        return "Faculty added";
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultyByUniversityId(@PathVariable Integer universityId) {
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        try {
            facultyRepository.deleteById(id);
            return "Faculty deleted";

        } catch (Exception e) {
            return "Error in deleting";
        }
    }

    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDto facultyDto){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()){
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getName());

            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if (!optionalUniversity.isPresent()){
                return "Such university was not found";
            }
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty edited";
        }
        return "Faculty was not found";
    }
}
