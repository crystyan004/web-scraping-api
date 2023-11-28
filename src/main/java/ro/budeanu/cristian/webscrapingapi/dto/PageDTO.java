package ro.budeanu.cristian.webscrapingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {
    private List<T> items;
    private int totalPages;
    private long totalElements;
    private boolean last;
    private boolean first;
}
