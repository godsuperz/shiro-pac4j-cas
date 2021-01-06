package com.superz.shirospringboot.common.shiro.realm;

import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jPrincipal;
import io.buji.pac4j.token.Pac4jToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.pac4j.core.profile.CommonProfile;

import java.util.List;

/**
 * @author zhang.chao
 * @date 2021/1/6
 */
public class CasRealm extends Pac4jRealm {

    private String clientName;

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        final Pac4jToken pac4jToken = (Pac4jToken) authenticationToken;
        final List<CommonProfile> commonProfileList = pac4jToken.getProfiles();
        final CommonProfile commonProfile = commonProfileList.get(0);
        System.out.println("单点登录返回的信息" + commonProfile.toString());
        // todo
        final Pac4jPrincipal principal = new Pac4jPrincipal(commonProfileList, getPrincipalNameAttribute());
        final PrincipalCollection principalCollection = new SimplePrincipalCollection(principal, getName());
        return new SimpleAuthenticationInfo(principalCollection, commonProfileList.hashCode());
    }

    /**
     * 授权/验权（todo 后续有权限在此增加）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        authInfo.addStringPermission("user");
        return authInfo;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
