/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller.dto;

import java.util.List;

/**
 *
 * @author TranVietDung
 */
public class WrapperReviewDto
{
    private List<ReviewDto> lsReviewDto;

    public WrapperReviewDto()
    {
    }

    public WrapperReviewDto(List<ReviewDto> lsReviewDto)
    {
        this.lsReviewDto = lsReviewDto;
    }

    public List<ReviewDto> getLsReviewDto()
    {
        return lsReviewDto;
    }

    public void setLsReviewDto(List<ReviewDto> lsReviewDto)
    {
        this.lsReviewDto = lsReviewDto;
    }

    @Override
    public String toString()
    {
        return "WrapperReviewDto{" + "lsReviewDto=" + lsReviewDto + '}';
    }
    
    
}
