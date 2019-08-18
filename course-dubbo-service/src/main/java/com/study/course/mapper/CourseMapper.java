package com.study.course.mapper;

import com.study.course.dto.CourseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("select * from pe_course")
    List<CourseDTO> listCourse();

    @Select("select user_id from pr_course_user where course_id=#{courseId}")
    Integer getCourseTeacher(@Param(value = "courseId")int courseId);
}
