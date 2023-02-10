package com.example.learn_connection_of_claasses_ford_atabase.controller;

import com.example.learn_connection_of_claasses_ford_atabase.entity.Faculty;
import com.example.learn_connection_of_claasses_ford_atabase.entity.Group;
import com.example.learn_connection_of_claasses_ford_atabase.entity.University;
import com.example.learn_connection_of_claasses_ford_atabase.payload.FacultyDto;
import com.example.learn_connection_of_claasses_ford_atabase.payload.GroupDto;
import com.example.learn_connection_of_claasses_ford_atabase.repository.FacultyRepository;
import com.example.learn_connection_of_claasses_ford_atabase.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepository;
//    For Ministry
    @GetMapping
    public List<Group> getGroup() {
        List<Group> groupList = groupRepository.findAll();
        return groupList;
    }

//    For university
    @GetMapping("/byUniversityId/{universityId}")
    public  List<Group> getGroupsByUniversityId(@PathVariable Integer universityId){
//        List<Group> groupsByUniversityId = groupRepository.getGroupsByUniversityId(universityId);
        List<Group> allByFaculty_university_id = groupRepository.findAllByFaculty_University_Id(universityId);
//        List<Group> groupsByUniversityIdNative = groupRepository.getGroupsByUniversityIdNative(universityId);
        return allByFaculty_university_id;
    }
    @PostMapping
    public  String addGroup(@RequestBody GroupDto groupDto){
        Group group = new Group();
        group.setName(groupDto.getName());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()){
            return "Such faculty not found";
        }
        group.setFaculty(optionalFaculty.get());

        groupRepository.save(group);
        return "Group added";
    }


    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Integer id) {
        try {
            groupRepository.deleteById(id);
            return "Group was deleted";

        } catch (Exception e) {
            return "Error in deleting";
        }
    }

    @PutMapping("/{id}")
    public String editGroup (@PathVariable Integer id, @RequestBody GroupDto groupDto){
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()){
            Group editGroup = optionalGroup.get();
            editGroup.setName(groupDto.getName());

            Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
            if (!optionalFaculty.isPresent()){
                return "Such faculty was not found";
            }
            editGroup.setFaculty(optionalFaculty.get());
            groupRepository.save(editGroup);
            return "Group edited";
        }
        return "Group was not found";
    }
}
