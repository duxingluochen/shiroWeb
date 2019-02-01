package com.tuke.realm;

import com.tuke.dao.UserDao;
import com.tuke.entity.User;
import com.tuke.util.DbUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.sql.Connection;

public class MyRealm extends AuthorizingRealm {

    private UserDao userDao=new UserDao();
    private DbUtil dbUtil = new DbUtil();
    /**
     *  2.查询当前用户授权和角色
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        //认证信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Connection con = null;
        //通过用户获取角色
        try {
           con = dbUtil.getCon();
            authorizationInfo.setRoles(userDao.getRoles(con, userName));
            authorizationInfo.setStringPermissions(userDao.getPermissions(con, userName));

        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e ){
                e.printStackTrace();
            }
        }

        return authorizationInfo;
    }

    /**
     * 1.身份认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String userName =(String) token.getPrincipal();
        //使用用户名查询数据库中的身份
        Connection con = null;
        try{
            con = dbUtil.getCon();
            User byUserName = userDao.getByUserName(con, userName);
            if(byUserName!=null){
                AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(byUserName.getUserName(),byUserName.getPassword(),"xx");
                return authcInfo;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
