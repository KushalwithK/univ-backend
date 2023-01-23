package com.univ.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamMemberUpdateModel {
    private String name;
    private String role;
    private String image;
    private String url;
}
