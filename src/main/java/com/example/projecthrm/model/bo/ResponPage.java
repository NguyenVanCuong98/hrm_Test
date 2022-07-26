package com.example.projecthrm.model.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponPage {
    Boolean status ;
    String message ;
    Integer activePage ;
    Integer totalPage ;
    Object result ;

    public ResponPage(Integer actPage)
    {
        this.activePage = actPage ;
    }
    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", activePage=" + activePage +
                ", totalPage=" + totalPage +
                ", result=" + result +
                '}';
    }
}
