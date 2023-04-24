package com.shubhammishra.blogsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentsPaginationDto {

    private List<CommentDto> content;
    public int pageNumber;
    public int pageSize;
    public long totalElements;
    public long totalPages;
    public boolean lastPage;
}
