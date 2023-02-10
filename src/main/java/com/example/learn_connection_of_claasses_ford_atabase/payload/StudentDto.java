package com.example.learn_connection_of_claasses_ford_atabase.payload;

import com.example.learn_connection_of_claasses_ford_atabase.entity.Address;
import com.example.learn_connection_of_claasses_ford_atabase.entity.Group;
import com.example.learn_connection_of_claasses_ford_atabase.entity.Subject;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private Integer address;
    private Integer group;
    private Integer subjects;
}
