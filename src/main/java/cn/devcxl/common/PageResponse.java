package cn.devcxl.common;

import cn.devcxl.common.exception.BadRequestException;
import cn.devcxl.common.exception.enums.CommonErrorCode;
import lombok.Data;

import java.util.List;

/**
 * @author devcxl
 */
@Data
public class PageResponse<T> {
    /**
     * 当前页码
     */
    private Integer pageIndex;
    /**
     * 每页大小
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer pageCount;

    /**
     * 总数据量
     */
    private Long dataCount;

    /**
     * 数据
     */
    private List<T> data;

    public PageResponse() {
    }

    public PageResponse(PageRequest pageRequest, Long dataCount, List<T> data) {
        this.pageIndex = pageRequest.getPageIndex();
        this.pageSize = pageRequest.getPageSize();
        this.dataCount = dataCount;
        this.pageCount = Math.toIntExact((dataCount % pageSize) > 0 ? (dataCount / pageSize) + 1 : dataCount / pageSize);
        this.data = data;
        if (pageCount > 0) {
            if (pageIndex > pageCount) {
                throw new BadRequestException(CommonErrorCode.BAD_REQUEST.setMessage("pageSize > pageCount"));
            }
        }

    }

}
