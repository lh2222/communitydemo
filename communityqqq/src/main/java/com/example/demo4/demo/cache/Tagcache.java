package com.example.demo4.demo.cache;


import com.example.demo4.demo.Dto.TagDto;
import org.thymeleaf.util.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 查到所有的tag
public class Tagcache {
    public static List<TagDto> get() {
        List<TagDto> tagDtos = new ArrayList<>();
        TagDto program = new TagDto();
        program.setCategoryname("开发语言");
        program.setTags(Arrays.asList("js", "java", "css"));
        tagDtos.add(program);

        TagDto framework = new TagDto();
        framework.setCategoryname("平台框架");
        framework.setTags(Arrays.asList("Spring", "springboot", "dubbo"));
        tagDtos.add(framework);

        TagDto service = new TagDto();
        service.setCategoryname("服务器");
        service.setTags(Arrays.asList("tomcat", "unix", "ngix"));
        tagDtos.add(service);
        return tagDtos;
    }

    public static String filterisValid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDto> tagDtos = get();
        List<String> tagList = tagDtos.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}

