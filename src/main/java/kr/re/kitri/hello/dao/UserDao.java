package kr.re.kitri.hello.dao;

import kr.re.kitri.hello.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    void insertUser(User user);
    List<User> selectAllUsers();
    User selectUserByUserId(String userId);
}
