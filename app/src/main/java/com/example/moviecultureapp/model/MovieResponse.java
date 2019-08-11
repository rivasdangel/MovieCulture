package com.example.moviecultureapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("page")
    private Integer page;
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("total_pages")
    private Integer totalPages;
    @SerializedName("results")
    private List<Movie> results;

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public List<Movie> getResults() {
        return results;
    }
}
