package com.example.projecthrm.repository;

import com.example.projecthrm.model.entity.Account;
import com.example.projecthrm.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByStatusId(Long postId, Pageable pageable);
    User findUserByAccount(Account account);
    @Query(value = "SELECT * from users",nativeQuery = true)
    Page<User> getPage(Pageable pageable);
    @Query(value = "SELECT  * from users where users.account_id is null order by users.status_id asc limit 1 ",nativeQuery = true)
    List<User> findUserOne();
    Optional<User> findByIdAndStatusId(Long id, Long statusId);

}
