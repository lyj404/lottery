package com.lyj.lotteradapter.web.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyj.common.annotation.ResponseResult;
import com.lyj.lotterclient.api.IUserService;
import com.lyj.lotterclient.dto.data.UserVO;
import com.lyj.lotterclient.dto.query.UserListByParamQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lyj
 * @date 2023-07-03
 **/
@ResponseResult
@RequiredArgsConstructor
@RequestMapping("/v1/admin/user")
public class AdminUserController {
    private final IUserService userService;

    /**
     * 分页查询用户
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @GetMapping("/page")
    public IPage<UserVO> page(@RequestBody UserListByParamQuery query) {
        return userService.page(query);
    }
}
