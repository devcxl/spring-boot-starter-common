package cn.devcxl.common;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @author devcxl
 */
@Data
public class PageRequest {
    /**
     * 当前页索引 must大于0
     */
    @NotNull(message = "not null")
    @Min(value = 1)
    private Integer pageIndex = 1;
    /**
     * 当前页面大小
     */
    @NotNull(message = "not null")
    @Min(value = 1)
    private Integer pageSize = 10;
    /**
     * 计算得出的数据库开始索引
     *
     * @ignore
     */
    private Integer startIndex;


    public PageRequest() {
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartIndex() {
        return (pageIndex - 1) * pageSize;
    }
}
