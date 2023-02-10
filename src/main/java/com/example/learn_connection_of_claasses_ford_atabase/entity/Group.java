package com.example.learn_connection_of_claasses_ford_atabase.entity;

import com.example.learn_connection_of_claasses_ford_atabase.entity.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Faculty faculty;
//    @OneToMany
//    private List<Student> students;
}
