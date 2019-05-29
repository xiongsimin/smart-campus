 //登陆时验证输入框中数据是否合法
    function login(){
        //不为空
        if($("#account").val()==null||$("#account").val()=="")
        {
            alert("账号不能为空");
            return false;
        }
        /*//不为非整数
        if(isNaN($("#account").val()))
        {
            $("#account").val("")
            alert("账号必须为数字");
            return false;
        }*/
        //不为空
        if($("#password").val()==null||$("#password").val()=="")
        {
            alert("账号不能为空");
            return false;
        }
        $("#sub").submit();
    }
    //注销登陆
   