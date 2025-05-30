package org.ame.yan.shabby.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {

    private Integer id;

    private String tagName;

    private Integer count;

}
