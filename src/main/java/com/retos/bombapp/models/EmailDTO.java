package com.retos.bombapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    @JsonProperty("destinatario")
    private String addressee;

    @JsonProperty("asunto")
    private String subject;

    @JsonProperty("html")
    private String html;
}
