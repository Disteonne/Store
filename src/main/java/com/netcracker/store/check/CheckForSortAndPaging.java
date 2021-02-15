package com.netcracker.store.check;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class CheckForSortAndPaging {

    public PageRequest returnPage(int page, int size, String sortBy, String sortOrder) {
        return PageRequest.of(page, size, Sort.Direction.fromString(sortOrder), sortBy);
    }
}
