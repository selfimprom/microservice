package com.study.course.controller;

import com.study.course.dto.CourseDTO;
import com.study.course.service.ICourseService;
import com.study.thrift.user.dto.UserDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/course")
@EnableAutoConfiguration
public class CourseController {
    @Reference(version = "${service.version}")
    private ICourseService courseService;

    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseDTO> courseList(HttpServletRequest request) {
        UserDTO userDTO = (UserDTO) request.getAttribute("user");
        System.out.println(userDTO.toString());
        List<CourseDTO> result = courseService.courseList();
        return result;
    }
}
