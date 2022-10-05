package com.retos.bombapp.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenDTO implements Serializable {
    @JsonProperty("sub")
    private String subject;

    @JsonProperty("iss")
    private String issuer;

    @JsonProperty("userNames")
    private String names;
   
    @JsonProperty("userLastnames")
    private String lastNames;
    
    @JsonProperty("userType")
    private String type;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("rolName")
    private String rolName;
    
    @JsonProperty("rolCode")
    private String rolCode;
}
