package com.demo.reactiveserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    private String userId;

    private String name;

    private String age;

    private String address;
}
