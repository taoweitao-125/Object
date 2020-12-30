$(function () {//网页加载完成后执行的方法逻辑
    //$("#id")通过id获取页面元素，绑定表单提交事件
    $("#login_form").submit(function () {
    //自己ajax提交变单数据
    $.ajax({
        url: "../login",//请求的服务路径
        type:"post",//请求方法
    //    请求数据类型，不是用默认为表单数据类型，如果是json要注明
    //    content-Type ：“application/json”
        data:$("#login_form").serialize(),//序列化表单数据格式
        dataType: "json",//相应的数据类型
        success:function(resp) {
            if(resp.success) {
                window.location.href = "../jsp/carsList.jsp";
            }else {
                alert("错误码"+resp.code + "\n错误信息：" + resp.message);
            }
        }
    })
    //    禁用表单默认提交
        return false;
    })

})
