package com.company.planner2.core;

import com.haulmont.addon.bproc.provider.UserProvider;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component(ManagerProvider.NAME)
public class ManagerProvider implements UserProvider {
    public static final String NAME = "planner2_ManagerProvider";
    private static final String UserRole = "financialManagerRole";
    @Inject
    private DataManager dataManager;

    @Override
    public User get(String executionId) {
        List<User> managers = dataManager.load(User.class)
                .query("select u from sec$User u join u.userRoles ur where ur.role.name = :rolename")
                .parameter("rolename", UserRole)
                .list();
        if(managers.isEmpty()) {
            throw new RuntimeException("No manager found");
        }
        return managers.get(0);
    }
}