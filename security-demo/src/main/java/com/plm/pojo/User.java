package com.plm.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import com.plm.validation.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author : cwh
 * 2019/2/20 0020
 * description ：
 */
public class User {

    public interface UserSimpleView {
    }

    ;

    public interface UserDetailView extends UserSimpleView {
    }

    ;

    private String id;

    @MyConstraint(message = "自定义验证注解")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;
    @Past(message = "生日必须大于当前时间")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
