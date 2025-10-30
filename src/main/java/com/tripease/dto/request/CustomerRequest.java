package com.tripease.dto.request;

import com.tripease.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRequest {

    private String name;

    private int age;

    private String email;

    private Gender gender;
}
