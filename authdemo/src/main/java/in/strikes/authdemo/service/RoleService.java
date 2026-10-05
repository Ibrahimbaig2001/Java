package in.strikes.authdemo.service;

import in.strikes.authdemo.entity.Role;
import in.strikes.authdemo.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    public void addRole(Role role){
        roleRepository.save(role);

    }
}
