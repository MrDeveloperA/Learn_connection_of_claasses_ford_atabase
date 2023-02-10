package com.example.learn_connection_of_claasses_ford_atabase.payload;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UniversityDto {
    private String name;
    private String city;
    private String district;
    private String street;
}
