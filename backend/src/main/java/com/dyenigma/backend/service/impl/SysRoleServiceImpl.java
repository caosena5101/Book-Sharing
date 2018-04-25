package com.dyenigma.backend.service.impl;

import com.dyenigma.backend.dao.SysRoleMapper;
import com.dyenigma.backend.dao.SysRolePmsnMapper;
import com.dyenigma.backend.entity.BaseDomain;
import com.dyenigma.backend.entity.SysRole;
import com.dyenigma.backend.entity.SysRolePmsn;
import com.dyenigma.backend.service.SysRoleService;
import com.dyenigma.backend.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * sharing/com.dyenigma.backend.service.impl
 *
 * @Description : 角色操作类，包含角色的CURD、角色的权限分配等
 * @Author : dingdongliang
 * @Date : 2018/4/18 15:20
 */
@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRolePmsnMapper sysRolePmsnMapper;

    /**
     * 给角色赋予权限，一对多
     *
     * @param roleId  角色ID
     * @param pmsnIds 权限ID数组
     * @return void
     * @author dingdongliang
     * @date 2018/4/24 8:33
     */
    @Override
    public void putPmsnToRole(String roleId, String[] pmsnIds) {
        String userId = ShiroUtil.getUserId();
        sysRolePmsnMapper.insertMany(roleId, Arrays.asList(pmsnIds), userId);
    }

    /**
     * 更新用户的角色
     *
     * @param roleId  角色ID
     * @param pmsnIds 权限ID数组
     * @return void
     * @author dingdongliang
     * @date 2018/4/24 9:31
     */
    @Override
    public void updatePmsnToRole(String roleId, String[] pmsnIds) {

        //首先获取该角色现有的权限集合，挑出其中的pmsnId为A集合
        List<SysRolePmsn> sysRolePmsnList = sysRolePmsnMapper.selectByRoleId(roleId);
        List<String> aList = new ArrayList<>();
        for (SysRolePmsn sysRolePmsn : sysRolePmsnList) {
            aList.add(sysRolePmsn.getPmsnId());
        }

        //新权限数组pmsnIds为B集合，准备获取A集合和B集合的交集
        List<String> bList = new ArrayList<>();
        for (String str : pmsnIds) {
            bList.add(str);
        }

        //复制A集合到C集合，因为在取交集的过程中，会更改集合本身，此时C集合==A集合
        List<String> cList = new ArrayList<>(aList);

        //此时C集合 == （A集合与B集合的交集）为角色依然拥有的权限
        cList.retainAll(bList);

        //更新A集合-C集合的差集为过期的权限，更新状态为"不可用",此时A集合已经全部为过期权限了
        aList.removeAll(cList);
        SysRolePmsn sysRolePmsn;

        //根据角色ID和权限ID查询，更新状态为过期
        for (String pmsnId : aList) {
            sysRolePmsn = sysRolePmsnMapper.selectByDoubleId(roleId, pmsnId);
            sysRolePmsn.setStatus("I");
            BaseDomain.updateLog(sysRolePmsn);
            sysRolePmsnMapper.updateByPrimaryKey(sysRolePmsn);
        }

        //取B集合-C集合的差集为新增的角色，批量添加
        bList.removeAll(cList);

        //批量插入的时候，如果集合为空，会报错，所以要判断是否为空，而不是null
        if (bList.size() > 0) {
            String userId = ShiroUtil.getUserId();
            sysRolePmsnMapper.insertMany(roleId, bList, userId);
        }
    }

    /**
     * 新增角色，同时分配权限
     *
     * @param sysRole 角色对象
     * @param pmsnIds 权限集合
     * @return void
     * @author dingdongliang
     * @date 2018/4/24 11:13
     */
    @Override
    public void insert(SysRole sysRole, String[] pmsnIds) {
        sysRoleMapper.insert(sysRole);
        putPmsnToRole(sysRole.getRoleId(), pmsnIds);
    }

    /**
     * 修改角色，同时修改角色-权限映射
     *
     * @param sysRole 角色对象
     * @param pmsnIds 权限集合
     * @return void
     * @author dingdongliang
     * @date 2018/4/24 11:13
     */
    @Override
    public void update(SysRole sysRole, String[] pmsnIds) {
        sysRoleMapper.updateByPrimaryKey(sysRole);
        updatePmsnToRole(sysRole.getRoleId(), pmsnIds);
    }

    @Override
    public void delete(String roleId) {
        sysRolePmsnMapper.deleteByRoleId(roleId);
        sysRoleMapper.deleteByPrimaryKey(roleId);
    }
}
