package com.github.bbodnar;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "student")
@JsonPropertyOrder({"firstName", "lastName", "age"})
public class Student {
    private String firstName;
    @JsonProperty("after_name")
    private String lastName;
    private int age;

}
