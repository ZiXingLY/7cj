package com.qy.service.impl;

import com.qy.dao.SysPermissionMapper;
import com.qy.model.SysPermission;
import com.qy.service.SysPermissionService;
import com.qy.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zaq on 2018/07/16.
 */
@Service
@Transactional
public class SysPermissionServiceImpl extends AbstractService<SysPermission> implements SysPermissionService {
    @Resource
    private SysPermissionMapper sysPermissionMapper;

}
