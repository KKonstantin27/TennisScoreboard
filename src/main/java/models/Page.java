package models;

import dto.MatchDTO;
import lombok.Data;

import java.util.List;

@Data
public class Page {
    public static final int PAGE_SIZE = 5;
    private int totalItems;
    private int totalPages;
    private int currentPage;
    private int currentPageItems;
    private List<MatchDTO> currentPageMatches;

    public Page(List<MatchDTO> matchesDTO, int totalItems, int currentPage) {
        this.totalItems = totalItems;
        this.currentPage = currentPage;
        double result = (double) totalItems / PAGE_SIZE;
        totalPages = (int) Math.ceil(result) == 0 ? 1 : (int) Math.ceil(result);
        currentPageItems = Math.min(totalItems - ((currentPage - 1) * 5), 5);
        currentPageMatches = matchesDTO.subList((PAGE_SIZE * currentPage) - 5, (Page.PAGE_SIZE * currentPage) - 5 + currentPageItems);
    }
}
