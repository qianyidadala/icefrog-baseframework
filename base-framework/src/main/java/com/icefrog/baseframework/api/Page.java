package com.icefrog.baseframework.api;

import lombok.Data;

/***
 * Page object
 * @param <T> params type
 *
 * @author icefrog.su@qq.com
 */
@Data
public class Page<T> {

    /***
     * 业务参数
     */
    private T params;

    /***
     * 分页页码
     */
    private Integer pageIndex;

    /***
     * 每页展示数量
     */
    private Integer pageSize;
}
