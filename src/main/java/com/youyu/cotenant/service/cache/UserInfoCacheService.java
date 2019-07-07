package com.youyu.cotenant.service.cache;

import com.youyu.cotenant.entity.CotenantUser;
import com.youyu.cotenant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 用户信息缓存
 *
 * @author USER
 */
@Component
public class UserInfoCacheService {

    @Autowired
    private UserService userService;

    @Cacheable(cacheNames = "cotenant_user", key = "#userName")
    public CotenantUser getUserCache(String userName) {
        CotenantUser user = userService.selectUserByUserName(userName);
        return user;
    }

    /**
     * 删除缓存
     *
     * @param userName
     */
    @CacheEvict(cacheNames = "cotenant_user", key = "#userName")
    public void removeCache(String userName) {
        return;
    }
}
