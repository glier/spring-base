package ru.gb.springbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springbase.model.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
