function post(){
    var questionid=$("#question_id").val();
    var content=$("#comment_content").val();
   comment2target(questionid,1,content);
}




function comment2target(targetId,type,content){
    if(!content){
        alert("不能回复空内容");
        return
    }
    $.ajax({
        type:"post",
        url:"/comment",
        contentType:'application/json',
        data: JSON.stringify({
            "parent_id":targetId,
            "content":content,
            "type":type
        }),
        success:function(response){
            if(response.code==200){
                $("#comment_section").hide();
                //  回复成功 重新刷新页面
                window.location.reload();
            }else{
                if(response.code==2003){
                    var isAccepted=confirm(response.message);
                    if(isAccepted){ // 重新打开一个窗
                        window.open("https://github.com/login/oauth/authorize?client_id=34822b8342f78338da41&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closeable",true);
                    }
                    alert(response.message);}
                else{
                    console.log(response);
                }

            }


        },
        dataType:"json"
    });
}
// 2级评论
function comment(e){
    var commentId=e.getAttribute("data-id");
    var content=$("#input-"+commentId).val();
    comment2target(commentId,2,content)
}












// 2级评论
function collapse1(e){
   var id=e.getAttribute("data-id");
   var comments=$("#comment-"+id);
   var coll=e.getAttribute("data-collapse");
   if(coll){
       comments.removeClass("in");
       e.removeAttribute("data-collapse");
    }else{ // 打开2级评论 发送请求 拼接请求
$.getJSON("/comment/" + id, function (data) {
    $.each(data.data.reverse(), function (index, comment) {
        var mediaLeftElement = $("<div/>", {
            "class": "media-left"
        }).append($("<img/>", {
            "class": "media-object img-rounded",
            "src": comment.user.avatarUrl
        }));

        var mediaBodyElement = $("<div/>", {
            "class": "media-body"
        }).append($("<h5/>", {
            "class": "media-heading",
            "html": comment.user.name
        })).append($("<div/>", {
            "html": comment.content
        })).append($("<span/>", {
            "class": "pull-right",

        }));

        var mediaElement = $("<div/>", {
            "class": "media-heading"
        }).append(mediaLeftElement).append(mediaBodyElement);

        var commentElement = $("<div/>", {
            "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 "
        }).append(mediaElement);

        subCommentContainer.prepend(commentElement);
    });
    //展开二级评论
    comments.addClass("in");
    // 标记二级评论展开状态
    e.setAttribute("data-collapse", "in");


        });



}}

function collapse(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
       // e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
           // e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatar_url
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<span/>", {
                        "class": "pull-right",

                    }));

                    var mediaElement = $("<div/>", {
                        "class": "media-heading"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 "
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
             //   e.classList.add("active");
            });
        }
    }
}

//function selectTag(e){
 //   var value=e.getAttribute("data-tag");
  //  var previous=$("#tag").val();
  //  if(previous.indexOf(value)!=-1){
    //    if(previous){
     //       $("#tag").val(previous+','+value);
     //   }else{
     //       $("#tag").val(value);
     //   }

   // }
//}
function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();

    if (previous) {
        var index = 0;
        var appear = false; //记录value是否已经作为一个独立的标签出现过
        while (true) {
            index = previous.indexOf(value, index); //value字符串在previous中出现的位置
            if (index == -1) break;
            //判断previous中出现的value是否是另一个标签的一部分
            //即value的前一个和后一个字符都是逗号","或者没有字符时，才说明value是一个独立的标签
            if ((index == 0 || previous.charAt(index - 1) == ",")
                && (index + value.length == previous.length || previous.charAt(index + value.length) == ",")
            ) {
                appear = true;
                break;
            }
            index++; //用于搜索下一个出现位置
        }
        if (!appear) {
            //若value没有作为一个独立的标签出现过
            $("#tag").val(previous + ',' + value);
        }
    }
    else {
        $("#tag").val(value);
    }
}

function showSelectTag(){
    $("#selectTag").show();
}