package com.plm.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.plm.dto.UserQueryCondition;
import com.plm.exception.UserException;
import com.plm.pojo.User;
import com.plm.security.app.social.sign.AppSignUpUtils;
import com.plm.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : cwh
 * 2019/2/19 0019
 * description ：
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "用户服务", tags = {"用户操作接口"})
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @Autowired
    private SecurityProperties securityProperties;

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {
        log.info("注册", user.toString());
        String userId = user.getUserName();
        // providerSignInUtils.doPostSignUp(userId,new ServletWebRequest(request));
        appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
    }

    @GetMapping("/me")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) throws Exception {
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");
        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                .parseClaimsJws(token).getBody();
        String company = (String) claims.get("company");
        String msg = (String) claims.get("msg");
        log.info(company + msg);
        return authentication;
    }

    /*@GetMapping("/me")
    public Object getCurrentUser(*//*Authentication authentication*//* @AuthenticationPrincipal UserDetails userDetails) {
       // return authentication;
        return userDetails;
    }*/

    /*@RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<User> query(@RequestParam(value = "userName",required = false,defaultValue = "tom") String nickname){
        System.out.println(nickname);
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }*/

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "查询", notes = "多条件查询")
    public List<User> query(UserQueryCondition condition, @PageableDefault(size = 10, page = 5, sort = "userName", direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());

        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getById(@PathVariable String id) {
        // throw new UserException("1","not exist");
        User user = new User();
        user.setUserName("tom");
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //bindingResult.getAllErrors().stream().forEach(error -> error.getDefaultMessage());
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }

        System.out.println(user);
        System.out.println(user.getUserName());
        System.out.println(user.getId());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody @ApiParam(value = "用户对象", required = true) User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user);
        System.out.println(user.getUserName());
        System.out.println(user.getId());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
   /* @ApiOperation(value = "删除", notes = "根据ID删除")
    @ApiImplicitParam(name = "id", paramType = "path", value = "试题ID", required = true,dataType = "String")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageNumber", value = "当前页数", required = true, dataType = "long"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页记录数", required = true, dataType = "long"),
            @ApiImplicitParam(paramType="query", name = "queryOptions", value = "查询条件", required=false, dataType = "String"),
    })*/
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }

}
