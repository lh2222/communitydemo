package com.example.demo4.demo.Service;

import com.example.demo4.demo.Dto.QuestionDto;
import com.example.demo4.demo.Exception.CustomizeException;
import com.example.demo4.demo.Exception.CustomizedErrorCodeenum;
import com.example.demo4.demo.Mapper.QuestionMapper;
import com.example.demo4.demo.Mapper.UserMapper;
import com.example.demo4.demo.entity.Question;
import com.example.demo4.demo.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.xml.ws.Action;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public List<QuestionDto> list() {
        List<Question> questions=questionMapper.list();
        List<QuestionDto> questionDtoList=new ArrayList<>();
        for(Question question:questions)
        {
           User user= userMapper.findById(question.getCreator());
           QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user );
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    public List<QuestionDto> list(int userid) {
        List<Question> questions=questionMapper.listbyuserid(userid);
        List<QuestionDto> questionDtoList=new ArrayList<>();
        for(Question question:questions)
        {
            User user= userMapper.findById(question.getCreator());
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user );
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    public QuestionDto getById(int id) {
        Question question=questionMapper.getById(id);
        if(question==null){
           //  throw new CustomizeException("你找的问题不在了,要不换个试试");
            throw new CustomizeException(CustomizedErrorCodeenum.QUESTION_NOT_FOUND);
        }
        QuestionDto questionDto=new QuestionDto();
        User user= userMapper.findById(question.getCreator());
        questionDto.setUser(user);
        BeanUtils.copyProperties(question,questionDto);
        return  questionDto;


    }

    public void createOrUpdate(Question question) {
        if(question.getId()==0){// 有问题
            questionMapper.create(question);
        }else{

        }
    }

    public void incView(int id) {
        Question question=questionMapper.getById(id);

        question.setView_count(question.getView_count()+1);
        questionMapper.update(question);
    }

    public List<QuestionDto> selectRelate(QuestionDto questionDto) {
        // 多个tag 要注意切分
        // 输入的tag以逗号隔开
        if(questionDto.getTag()==null)
        {
            return new ArrayList<>();
        }else{
        String[] tags=StringUtils.split(questionDto.getTag(),",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question =new Question();
        question.setId(questionDto.getId());
        question.setTag(regexpTag);
        List<Question> questions=questionMapper.relatequestion(question);
       List<QuestionDto> questionDtoList= questions.stream().map(q->{QuestionDto questionDto1= new QuestionDto();
       BeanUtils.copyProperties(q,questionDto1);return questionDto1;
        }).collect(Collectors.toList());
        return questionDtoList;
        }

    }
}
