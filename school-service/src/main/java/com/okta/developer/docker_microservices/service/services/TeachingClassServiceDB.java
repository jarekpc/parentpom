package com.okta.developer.docker_microservices.service.services;

import com.okta.developer.docker_microservices.service.dao.TeachingClassDao;
import com.okta.developer.docker_microservices.service.dtos.TeachingClassDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeachingClassServiceDB implements TeachingClassService {

    private final TeachingClassDao teachingClassDao;

    @Autowired
    public TeachingClassServiceDB(TeachingClassDao teachingClassDao) {
        this.teachingClassDao = teachingClassDao;
    }

    @Override
    public List<TeachingClassDto> listClasses() {
        return teachingClassDao.findAll().stream().map(classObj1 -> TeachingClassDto.builder()
                .teacherName(classObj1.getTeacher().getName())
                .courseId(classObj1.getTeacher().getId())
                .numberOfStudents(classObj1.getStudents().size())
                .classId(classObj1.getId())
                .courseId(classObj1.getCourse().getId())
                .courseName(classObj1.getCourse().getName())
                .year(classObj1.getYear()).build()).collect(Collectors.toList());
    }
}
